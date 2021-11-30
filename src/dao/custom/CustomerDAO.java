package dao.custom;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO<Customer, String> {

    boolean add(Customer dto) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    ResultSet countCustomers() throws SQLException, ClassNotFoundException;

    boolean delete(java.lang.String id) throws SQLException, ClassNotFoundException;
    Order search(java.lang.String id) throws SQLException, ClassNotFoundException;

    Object getAll() throws SQLException, ClassNotFoundException;

    boolean update(Hibernate.entity.Customer customer) throws SQLException, ClassNotFoundException;
}
