package com.revature.daos;

import com.revature.models.User;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;

import java.sql.*;

public class UserDaoImpli implements UserDao {
    public boolean createUser(User user){
        String sql = "insert into user (type, firstName, lastName, email, username, password, checking, savings) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setDouble(7, user.getChecking());
            ps.setDouble(8, user.getSavings());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deposit(User user, int amount, int account){
        String sql = "update user set checking = ?";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

            user.setChecking(user.deposit(user, amount, account));
            ps.setDouble(7, user.getChecking());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public boolean withdraw(User user, int amount, int account){
        String sql = "update user set checking = ?";
        
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

            user.setChecking(user.withdraw(user, amount, account));
            ps.setDouble(7, user.getChecking());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
