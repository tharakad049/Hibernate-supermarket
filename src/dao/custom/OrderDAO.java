package dao.custom;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO <Order, String> {
    boolean add(Order dto) throws SQLException, ClassNotFoundException;

    boolean update(Order orderDTO) throws SQLException, ClassNotFoundException;

    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;

    boolean add(Hibernate.entity.Order dto) throws SQLException, ClassNotFoundException;

    boolean delete(java.lang.String s) throws SQLException, ClassNotFoundException;

    boolean update(Hibernate.entity.Order orderDTO) throws SQLException, ClassNotFoundException;

    Hibernate.entity.Order search(java.lang.String oid) throws SQLException, ClassNotFoundException;

    List<Customer> getAll() throws SQLException, ClassNotFoundException;

    boolean ifOrderExist(java.lang.String oid) throws SQLException, ClassNotFoundException;

    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
