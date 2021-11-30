package dao;

import dao.custom.impl.*;

import java.sql.SQLException;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public Object getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl() {
                    @Override
                    public boolean add(Object dto) throws SQLException, ClassNotFoundException {
                        return false;
                    }

                    @Override
                    public boolean ifItemExist(Object code) throws SQLException, ClassNotFoundException {
                        return false;
                    }
                };
            case ORDER:
                return new OrderDAOImpl() {
                    @Override
                    public boolean add(Object dto) throws SQLException, ClassNotFoundException {
                        return false;
                    }

                    @Override
                    public boolean update(Object orderDTO) throws SQLException, ClassNotFoundException {
                        return false;
                    }

                    @Override
                    public boolean ifOrderExist(Object oid) throws SQLException, ClassNotFoundException {
                        return false;
                    }
                };
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl() {
                    @Override
                    public boolean add(Object dto) throws SQLException, ClassNotFoundException {
                        return false;
                    }
                };
            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO
    }

}
