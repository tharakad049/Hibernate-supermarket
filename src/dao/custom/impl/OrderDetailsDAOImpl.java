package dao.custom.impl;

import Hibernate.entity.Customer;
import Hibernate.entity.Order;
import Hibernate.entity.OrderDetails;
import dao.CrudUtil;
import dao.custom.OrderDetailsDAO;

import java.sql.SQLException;
import java.util.List;

public abstract class OrderDetailsDAOImpl implements OrderDetailsDAO {

    //@Override
    public boolean add(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `OrderDetails` (OrderId, Code, UnitPrice, Qty) VALUES (?,?,?,?)", dto.getOid(),dto.getItemCode(), dto.getUnitPrice(), dto.getQty());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails orderDetailDTO) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
