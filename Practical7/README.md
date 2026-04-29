# Practical 7: Hibernate Mapping and HQL

This folder implements only Practical 7 from the AJP manual for the Campus Placement project.

## Objective Covered

Use Object Relational Mapping, prepare:

* one Hibernate configuration file
* one Hibernate mapping file for one table
* one console Java test class that runs HQL instead of SQL

## Table Used

`companies`

## Important Files

* `src/main/resources/hibernate.cfg.xml`
* `src/main/resources/hibernate/Company.hbm.xml`
* `src/main/java/com/campus/practical7/model/Company.java`
* `src/main/java/com/campus/practical7/main/HibernateTest.java`

## Run

```powershell
mvn clean package
mvn exec:java -Dexec.mainClass="com.campus.practical7.main.HibernateTest"
```

If the `exec:java` goal is not available in your Maven setup, run `HibernateTest` from your IDE.
