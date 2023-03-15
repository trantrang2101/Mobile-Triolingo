package com.example.triolingo_mobile.DAO;

import com.example.triolingo_mobile.DataAccess.DbContext;
import com.example.triolingo_mobile.Model.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DbContext {
    private static UserDAO instance;
    private static String DB_TABLE_NAME="[User]";
    public static final String ID_COLUMN = "Id";
    public static final String FULLNAME_COLUMN = "FullName";
    public static final String EMAIL_COLUMN = "Email";
    public static final String PASSWORD_COLUMN = "Password";
    public static final String AVATARURL_COLUMN = "AvatarUrl";
    public static final String STATUS_COLUMN = "Status";
    public static final String NOTE_COLUMN = "Note";

    public static UserDAO getInstance() {
        if (UserDAO.instance == null) {
            UserDAO.instance = new UserDAO();
        }
        return UserDAO.instance;
    }

    public UserEntity GetUserById(int id) {
        String sql = "select * from " + DB_TABLE_NAME + " where " + ID_COLUMN + " = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                UserEntity us = new UserEntity();
                us.setId(id);
                us.setFullNamel(rs.getString(FULLNAME_COLUMN));
                us.setEmail(rs.getString(EMAIL_COLUMN));
                us.setAvatarUrl(rs.getString(AVATARURL_COLUMN));
                us.setPassword(rs.getString(PASSWORD_COLUMN));
                us.setStatus(rs.getInt(STATUS_COLUMN));
                us.setNote(rs.getString(NOTE_COLUMN));
                return us;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int udpateUser(UserEntity us) {
        String sql = "update " + DB_TABLE_NAME + " set "
                + FULLNAME_COLUMN + " = ?, "
                + EMAIL_COLUMN + " = ?,"
                + AVATARURL_COLUMN +" = ?,"
                + PASSWORD_COLUMN + " = ? where " + ID_COLUMN + " = ?";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, us.getFullNamel());
            pre.setString(2, us.getEmail());
            pre.setString(3, us.getAvatarUrl());
            pre.setString(4, us.getPassword());
            pre.setInt(5, us.getId());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

}
