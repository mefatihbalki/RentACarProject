package dao;

import core.DB;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = DB.getInstance();
    }

    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }


    // Databasedeki kullanıcı adı ve şifre ile eşleşme durumunu kontrol eder.
    public User findByLogin(String username, String password){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return obj;
    }

    public  User match(ResultSet rs){
        User obj = new User();
        try {
            obj.setId(rs.getInt("user_id"));
            obj.setUsername(rs.getString("user_name"));
            obj.setPassword(rs.getString("user_password"));
            obj.setRole(rs.getString("user_role"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }
}
