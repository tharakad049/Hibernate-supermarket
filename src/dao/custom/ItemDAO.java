package dao.custom;

import Hibernate.entity.Item;
import dao.CrudDAO;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, String> {
    boolean add(Item dto) throws SQLException, ClassNotFoundException;

    boolean ifItemExist(String code) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
