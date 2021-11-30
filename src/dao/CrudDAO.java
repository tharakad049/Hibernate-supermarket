package dao;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<S, ID> extends SuperDAO {
    boolean add(S t) throws SQLException, ClassNotFoundException;
    boolean update(S s) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    Order search(ID id) throws SQLException, ClassNotFoundException;
    List<Customer> getAll() throws SQLException, ClassNotFoundException;
}
