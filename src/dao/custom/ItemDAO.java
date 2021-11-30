package dao.custom;

import Hibernate.entity.Item;

import java.sql.SQLException;

public interface ItemDAO <Item, String> {
    boolean add(Item dto) throws SQLException, ClassNotFoundException;
    boolean ifItemExist(String code) throws SQLException, ClassNotFoundException;

    boolean ifItemExist(java.lang.String code) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
    Hibernate.entity.Item search(java.lang.String itemCode) throws SQLException, ClassNotFoundException;
    boolean update(Hibernate.entity.Item search) throws SQLException, ClassNotFoundException;
    Object getAll() throws SQLException, ClassNotFoundException;

    boolean add(Hibernate.entity.Item dto) throws SQLException, ClassNotFoundException;

    boolean delete(java.lang.String code) throws SQLException, ClassNotFoundException;
}
