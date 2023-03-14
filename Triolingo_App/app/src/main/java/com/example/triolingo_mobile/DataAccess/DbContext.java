package com.example.triolingo_mobile.DataAccess;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbContext {
    Connection connect;
    String username, pass, ip, port, database;

    public Connection ConnectionClass() {
        ip = "192.168.1.40";
        database = "TriolingoDatabase";
        username = "sa1";
        pass = "123456";
        port = "1433";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection con = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"
                    + port+";"+ "databasename="+ database+";user="
                    +username+";password="+pass+";";
            con = DriverManager.getConnection(connectionURL);
        }
        catch (Exception ex) {
            Log.e("Error:", ex.getMessage() );
        }
        return con;
    }
}
