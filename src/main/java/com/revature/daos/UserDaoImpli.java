package com.revature.daos;

import com.revature.models.User;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpli implements UserDao {
    public boolean createUser(User user){
        String sql = "insert into user (type, firstName, lastName, email, password) values (?, ?, ?, ?, ?)";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());

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
        //update user set account type where account type = ?
        if(account == 1) {
            String sql = "update user set checking = ?";
            try(Connection c = ConnectionUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);){

                user.setChecking(user.deposit(user, amount, account));
                ps.setDouble(1, user.getChecking());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }else if (account == 2){
            String sql = "update user set savings = ?";
            try(Connection c = ConnectionUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);){

                user.setChecking(user.deposit(user, amount, account));
                ps.setDouble(1, user.getSavings());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else{
            return false;
        }
        return false;

    }

    public boolean withdraw(User user, int amount, int account){
        if(account == 1) {
            if (amount < 0 || amount > user.getChecking()){
                return false;
            }
            String sql = "update user set checking = ?";
            try(Connection c = ConnectionUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);){

                user.setChecking(user.withdraw(user, amount, account));
                ps.setDouble(1, user.getChecking());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }else if (account == 2){
            if (amount < 0 || amount > user.getSavings()){
                return false;
            }
            String sql = "update user set savings = ?";
            try(Connection c = ConnectionUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);){

                user.setChecking(user.withdraw(user, amount, account));
                ps.setDouble(1, user.getSavings());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1){
                    return true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else{
            return false;
        }
        return false;

    }

    public boolean transfer(User user, int amount, int order){
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

                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        String sql = "select * from user";
        List<User> users = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement();){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                User user = new User();
                int id = rs.getInt("id");
                user.setUserId(id);

                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                user.setType(types[typeOrdinal]);
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getByUserId(int id) {
        String sql = "select * from user where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                User user = new User();
                user.setUserId(id);

                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                user.setType(types[typeOrdinal]);

                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "update user set type = ?, firstname = ?, lastname = ?, email = ?, password = ? where id = ?";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getUserId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
