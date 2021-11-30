package bo.custom.impl;

import Hibernate.entity.Customer;
import Hibernate.entity.Item;
import Hibernate.entity.Order;
import Hibernate.entity.OrderDetails;
import Hibernate.util.FactoryConfiguration;
import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = (Connection) FactoryConfiguration.getInstance().getSession();
        boolean orderAvailable = orderDAO.ifOrderExist(dto.getOrderId());
        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);
        Order order = new Order(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId());
        boolean orderAdded = orderDAO.add(order);
        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : dto.getOrderDetail()) {
            OrderDetails orderDetailDTO = new OrderDetails(dto.getOrderId(), detail.getItemCode(), detail.getQty(), detail.getUnitPrice());
            boolean orderDetailsAdded = orderDetailsDAO.add(orderDetailDTO);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Item search = itemDAO.search(detail.getItemCode());
            search.setQtyOnHand(search.getQtyOnHand() - detail.getQty());
            boolean update = itemDAO.update(search);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return (String) orderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        AbstractList<Item> all = (AbstractList<Item>) customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(), c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode()));
        }
        return allCustomers;

    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        AbstractList<Item> all = (AbstractList<Item>) itemDAO.getAll();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand()));
        }
        return allItems;

    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(),  item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean ifItemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExist(code);
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExist(id);
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(s);
        return new CustomerDTO(c.getId(), c.getTitle(), c.getName(), c.getAddress(), c.getCity(), c.getProvince(), c.getPostalCode());
    }
}
