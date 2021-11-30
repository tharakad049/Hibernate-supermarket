package dao.custom.impl;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;
import dao.CrudUtil;
import dao.custom.OrderDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Orders` (OrderId, OrderDate, CustomerId) VALUES (?,?,?)", dto.getOid(), dto.getDate(), dto.getCustomerID());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Order orderDTO) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Order search(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Orders` WHERE OrderId=?", oid);
        rst.next();
        return new Order(rst.getString("oId"), LocalDate.parse(rst.getString("date")), rst.getString("customerID"));
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderId FROM `Orders` WHERE OrderId=?", oid);
        return rst.next();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderId FROM `Orders` ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("OD-%03d", (Integer.parseInt(rst.getString("OrderId").replace("OD-", "")) + 1)) : "OD-001";
    }
}
