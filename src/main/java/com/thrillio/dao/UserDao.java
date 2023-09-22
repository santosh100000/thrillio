package com.thrillio.dao;

import com.thrillio.constants.Gender;
import com.thrillio.constants.UserType;
import com.thrillio.dataStore.DataStore;
import com.thrillio.entities.User;
import com.thrillio.services.UserService;

import java.sql.*;
import java.util.List;

public class UserDao {
    public List<User> getUsers(){
        return DataStore.getUsers();
    }

    public User getUser(long userId) {
        User user = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "Nab74561$");
             Statement stmt = conn.createStatement();) {
            String query = "Select * from User where id = " + userId;
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int gender_id = rs.getInt("gender_id");
                Gender gender = Gender.values()[gender_id];
                int user_type_id = rs.getInt("user_type_id");
                UserType userType = UserType.values()[user_type_id];

                user = UserService.getInstance().createUser(id, email, password, firstName, lastName, gender, userType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}
