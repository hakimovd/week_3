package com.hakimov.supplier.dao;

import com.hakimov.supplier.models.Supplier;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/suppliers";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public List<Supplier> showAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM supplier";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt("id"));
                supplier.setOrganisation(resultSet.getString("organisation"));
                supplier.setAddress(resultSet.getString("address"));

                suppliers.add(supplier);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return suppliers;
    }

    public Supplier show (int id) {
        Supplier supplier = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM supplier WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            supplier = new Supplier();

            supplier.setId(resultSet.getInt("id"));
            supplier.setOrganisation(resultSet.getString("organisation"));
            supplier.setAddress(resultSet.getString("address"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

    public void save (Supplier supplier) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO supplier (organisation, address) VALUES (?, ?)");

            preparedStatement.setString(1, supplier.getOrganisation());
            preparedStatement.setString(2, supplier.getAddress());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (int id, Supplier updatedSupplier) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE supplier SET organisation=?, address=? WHERE id=?");

            preparedStatement.setString(1, updatedSupplier.getOrganisation());
            preparedStatement.setString(2, updatedSupplier.getAddress());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete (int id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement =
                    connection.prepareStatement("DELETE FROM supplier WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
