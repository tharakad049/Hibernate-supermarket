package dao.custom;

import Hibernate.entity.Customer;
import dao.CrudDAO;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    boolean add(Customer dto) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    ResultSet countCustomers() throws SQLException, ClassNotFoundException;
}
