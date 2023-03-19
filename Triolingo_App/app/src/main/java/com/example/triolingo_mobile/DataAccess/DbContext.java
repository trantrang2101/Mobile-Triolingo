package com.example.triolingo_mobile.DataAccess;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContext {
    String username, pass, ip, port, database;

    public Connection conn = ConnectionClass();

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            Log.e("Error:", e.getMessage() );
        }
        return rs;
    }

    public int countRows(String table, String search, String addCondition) {
        int te = 0;
        String sql = "CALL countRows('\\'%" + search + "%\\'','" + table + "','" + addCondition + "')";
        ResultSet rs1 = getData(sql);
        try {
            while (rs1.next()) {
                String sql1 = rs1.getString(1);
                ResultSet rs = getData(sql1);
                while (rs.next()) {
                    te = rs.getInt(1);
                }
            }
            rs1.close();
        } catch (Exception ex) {
            Log.e("Error:", ex.getMessage() );
        }
        return te;
    }

    public Connection ConnectionClass() {
        ip = "192.168.56.1";
        database = "TriolingoDatabase";
        username = "sa";
        pass = "123";
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
