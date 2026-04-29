package com.campus.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.campus.dao.CompanyDAO;
import com.campus.model.Company;

public class DriveSearchServer {
    private static final int PORT = 5050;

    public static void main(String[] args) {
        CompanyDAO companyDAO = new CompanyDAO();
        System.out.println("Campus Placement Drive Search Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket client = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                    String searchText = in.readLine();
                    List<Company> matches = companyDAO.searchCompanies(searchText == null ? "" : searchText.trim());
                    if (matches.isEmpty()) {
                        out.println("No placement drive found.");
                    } else {
                        for (Company company : matches) {
                            out.println(company.toString());
                        }
                    }
                    out.println("END");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
