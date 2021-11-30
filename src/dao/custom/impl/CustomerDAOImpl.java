package dao.custom.impl;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;
import dao.CrudUtil;
import dao.custom.CustomerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer (Id,Title, Name, Address,city, Province, PostalCode) VALUES (?,?,?,?,?,?,?)", dto.getId(), dto.getTitle(),dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE Id=?", id);
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET  Title=?, Name=?, Address=?, City=?, Province = ?, PostalCode=? WHERE Id=?", dto.getName(),dto.getTitle(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode(), dto.getId());
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE Id=?", id);
        rst.next();
        return (Order) new Order(id,rst.getString("Title"), rst.getString("Name"), rst.getString("Address"), rst.getString("City"), rst.getString("Province"), rst.getString("PostalCode"));
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString("Id"), rst.getString("Title"), rst.getString("Name"), rst.getString("Address"), rst.getString("City"), rst.getString("Province"), rst.getString("PostalCode")));
        }
        return allCustomers;
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT id FROM Customer WHERE Id=?", id).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C-", "")) + 1;
            return String.format("C-%03d", newCustomerId);
        } else {
            return "C-001";
        }
    }

    @Override
    public ResultSet countCustomers() throws SQLException, ClassNotFoundException {
       return CrudUtil.executeQuery("SELECT Count(*)FROM Customer");
    }
}
