package dao.custom;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;
import Hibernate.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO <OrderDetails,String> {
    boolean add(OrderDetails dto) throws SQLException, ClassNotFoundException;

    boolean delete(java.lang.String s) throws SQLException, ClassNotFoundException;

    boolean update(Hibernate.entity.OrderDetails orderDetailDTO) throws SQLException, ClassNotFoundException;

    Order search(java.lang.String s) throws SQLException, ClassNotFoundException;

    List<Customer> getAll() throws SQLException, ClassNotFoundException;
}
