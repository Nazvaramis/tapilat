package com.example.restapiproiect.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DataBaseConfig{

    // ase conectezi , URL il iei din MYSQL workshop , useru il faci cand creezi node parca , si a parola o setezi
    // tot tu
    private static String URL = "jdbc:mysql://localhost:3306/hospital";
    private static String USER = "hospital_user";

    private static String PASSWORD ="x3y4z6u25";

    public static Connection getConnection() throws SQLException{

        return DriverManager.getConnection(URL,USER,PASSWORD);

    }

}


