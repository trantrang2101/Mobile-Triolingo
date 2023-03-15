package com.example.triolingo_mobile.DataAccess;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContext {
    private static final String ip = "192.168.1.28";
    private static final String database = "TriolingoDatabase";
    private static final String username = "sa1";
    private static final String pass = "123456";
    private static final String port = "1433";
    private static final String DB_URL = "jdbc:jtds:sqlserver://"+ ip + ":"
            + port+";"+ "databasename="+ database+";user="
            +username+";password="+pass+";allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";

    public Connection conn = getConnection();

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

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception ex) {
            Log.e("Error:", ex.getMessage() );
        }
        return conn;
    }
}
