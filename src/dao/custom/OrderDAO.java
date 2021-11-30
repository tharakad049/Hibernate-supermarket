package dao.custom;

import Hibernate.entity.Order;
import dao.CrudDAO;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order, String> {
    boolean add(Order dto) throws SQLException, ClassNotFoundException;

    boolean update(Order orderDTO) throws SQLException, ClassNotFoundException;

    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
