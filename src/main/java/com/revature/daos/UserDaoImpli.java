package com.revature.daos;

import com.revature.models.User;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;

import java.sql.*;

public class UserDaoImpli implements UserDao {
    public boolean createUser(User user){
        String sql = "insert into user (type, firstName, lastName, email, password, checking, savings) values (?, ?, ?, ?, ?, ?, ?)";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setDouble(6, user.getChecking());
            ps.setDouble(7, user.getSavings());

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

    public User getByUserAndPass(String email, String pass){
        String sql = "select * from user where email = ? and password = ?";
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));

                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                user.setType(types[typeOrdinal]);

                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
