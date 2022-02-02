package com.revature.daos;

import com.revature.models.User;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpli implements UserDao {
    public boolean createUser(User user) {
        String sql = "insert into users (type, firstName, lastName, email, password) values (?, ?, ?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deposit(User user, int id, int account) {
        if (account == 1) {
            String sql = "update users set checking = ? where id = ?";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);) {

                //check if the amount inputted is negative

                ps.setDouble(1, user.getChecking());
                ps.setInt(2, id);


                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } else if (account == 2) {
            String sql = "update users set savings = ? where id = ?";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);) {

                ps.setDouble(1, user.getSavings());
                ps.setInt(2, id);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return false;

    }

    public boolean withdraw(User user, int id, int account) {
        if (account == 1) {
            String sql = "update users set checking = ? where id = ?";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);) {

                //check if the amount inputted is more than available or negative

                ps.setDouble(1, user.getChecking());
                ps.setInt(2, id);


                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } else if (account == 2) {
            String sql = "update users set savings = ? where id = ?";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);) {

                ps.setDouble(1, user.getSavings());
                ps.setInt(2, id);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean transfer(User user, int id, int order) {
        if (order == 1) {
            boolean check = this.withdraw(user,id,1);
            if (!check){
                return false;
            }
            this.deposit(user,id,2);
        } else if (order == 2) {
            boolean check = this.withdraw(user, id, 2);
            if (!check){
                return false;
            }
            this.deposit(user, id, 1);
        }
        return true;
    }

    public User getByUserAndPass(String email, String pass) {
        String sql = "select * from users where email = ? and password = ?";
        try (Connection c = ConnectionUtil.getConnection();
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
                user.setChecking(rs.getDouble("checking"));
                user.setSavings(rs.getDouble("savings"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAll() {
        String sql = "select * from users";
        List<User> users = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
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
                user.setChecking(rs.getDouble("checking"));
                user.setSavings(rs.getDouble("savings"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getByUserId(int id) {
        String sql = "select * from users where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(id);

                int typeOrdinal = rs.getInt("type");
                Type[] types = Type.values();
                user.setType(types[typeOrdinal]);

                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setChecking(rs.getDouble("checking"));
                user.setSavings(rs.getDouble("savings"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "update users set type = ?, firstname = ?, lastname = ?, email = ?, password = ? where id = ?";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, user.getType().ordinal());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getUserId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteUser(User user, int id) {
        String sql = "delete from users where id = ?";
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);

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
