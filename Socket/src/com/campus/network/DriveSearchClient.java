package com.campus.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DriveSearchClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.print("Enter company name or job role to search: ");
            out.println(scanner.nextLine());

            String response;
            while ((response = in.readLine()) != null && !"END".equals(response)) {
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println("Start DriveSearchServer first.");
            e.printStackTrace();
        }
    }
}
