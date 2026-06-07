package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class NorthwindDBApp {

    public static void main(String[] args) {

        //Create a datasource so we can connect to the database
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        //Do this for now but this is a bad way due to adding credentials inside code.
        //Bad security
        dataSource.setUsername("root");
        dataSource.setPassword("*******");

        String sql = """
                select
                    productName,
                    productID,
                    UnitPrice,
                    UnitsInStock
                from
                    northwind.products    
                """;

        try (

                //This is like clicking connect to local server in workbench
                Connection conn = dataSource.getConnection();
                //This is like typing the query in the query windows
                PreparedStatement stmt = conn.prepareStatement(sql);
                //Like clicking the lightning bolt
                ResultSet results = stmt.executeQuery();

        ){

            while(results.next()){
                //get the film_id from the result and store it in a java variable
                //Since it's an int in the DB we create an int variable
                //On the results we have a method for each datatype
                //Save it to a variable to SOUT out.
                String productName = results.getString("productName");
                int productID = results.getInt("productID");
                double unitPrice = results.getDouble("UnitPrice");
                int unitsInStock = results.getInt("unitsInStock");



                System.out.println("Product Name: " + productName);
                System.out.println("Product ID: " + productID);
                System.out.println("Unit Price: " + unitPrice);
                System.out.println("Units in Stock: " + unitsInStock);

            }

        } catch (SQLException e) {
            System.out.println("the db stuff failed");
            throw new RuntimeException(e);
        }
    }

    }


