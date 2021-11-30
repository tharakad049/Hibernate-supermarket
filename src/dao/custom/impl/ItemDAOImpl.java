package dao.custom.impl;

import Hibernate.entity.Item;
import Hibernate.entity.Order;
import dao.CrudUtil;
import dao.custom.ItemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item (code, description, packSize,  unitPrice, qtyOnHand) VALUES (?,?,?,?,?)", dto.getItemCode(), dto.getDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE Code=?", code);
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET description=?, packSize=?, unitPrice=?, qtyOnHand=? WHERE code=?", dto.getDescription(), dto.getPackSize(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getItemCode());
    }

    @Override
    public Order search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE Code=?", code);
        rst.next();
        return new Order(code, rst.getString("description"), rst.getString("packSize"), rst.getInt("qtyOnHand"), rst.getBigDecimal("unitPrice"));
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(rst.getString("code"), rst.getString("description"), rst.getString("packSize"), rst.getInt("qtyOnHand"), rst.getBigDecimal("unitPrice")));
        }
        return allItems ;
    }



    @Override
    public boolean ifItemExist(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT Code FROM Item WHERE Code=?", code).next();
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT Code FROM Item ORDER BY Code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("Code");
            int newItemId = Integer.parseInt(id.replace("I-", "")) + 1;
            return String.format("I-%03d", newItemId);
        } else {
            return "I-001";
        }
    }
}
