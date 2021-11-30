package dao.custom;

import Hibernate.entity.OrderDetails;
import dao.CrudDAO;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    boolean add(OrderDetails dto) throws SQLException, ClassNotFoundException;
}
