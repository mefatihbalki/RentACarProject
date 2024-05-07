package dao;

import core.DB;
import entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDao {
    private Connection con;
    private final ModelDao modelDao;
    private final BrandDao brandDao;

    public CarDao() {
        this.con = DB.getInstance();
        this.modelDao = new ModelDao();
        this.brandDao = new BrandDao();
    }

    public Car getById(int id) {
        Car obj = null;
        String query = "SELECT * FROM public.car WHERE car_id = ? ";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Car> findByAll() {
        return this.selectByQuery("SELECT * FROM public.car ORDER BY car_id");
    }

    public ArrayList<Car> selectByQuery(String query) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                cars.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public Car match(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setModel_id(rs.getInt("car_model_id"));
        car.setPlate(rs.getString("car_plate"));
        car.setColor(Car.Color.valueOf(rs.getString("car_color")));
        car.setKm(rs.getInt("car_km"));
        car.setModel(this.modelDao.getById(car.getModel_id()));
        return car;
    }

    public boolean update(Car car) {
        String query = "UPDATE public.car SET " +
                "car_model_id = ?, " +
                "car_color = ?, " +
                "car_km = ?, " +
                "car_plate = ? " +
                "WHERE car_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, car.getModel_id());
            ps.setString(2, car.getColor().toString());
            ps.setInt(3, car.getKm());
            ps.setString(4, car.getPlate());
            ps.setInt(5, car.getId());
            return ps.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Car car) {
        String query = "INSERT INTO public.car " +
                "(" +
                "car_model_id," +
                "car_color," +
                "car_km," +
                "car_plate" +
                ")" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, car.getModel_id());
            ps.setString(2, car.getColor().toString());
            ps.setInt(3, car.getKm());
            ps.setString(4, car.getPlate());
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int carId) {
        String query = "DELETE FROM public.car WHERE car_id = ? ";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, carId);
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
