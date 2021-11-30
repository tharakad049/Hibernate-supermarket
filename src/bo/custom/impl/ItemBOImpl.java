package bo.custom.impl;

import Hibernate.entity.Item;
import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        AbstractList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new ItemDTO(i.getItemCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand()));
        }
        return allItems;

    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getCode(), dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getDescription(), dto.getPackSize(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }

    @Override
    public boolean ifItemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(code);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }
}
