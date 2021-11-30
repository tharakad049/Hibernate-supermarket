package Hibernate.util;

import net.sf.jasperreports.engine.JRDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().
                /*addAnnotatedClass(Customer.class).*/addAnnotatedClass(Order.class)
               /* .addAnnotatedClass(OrderDetails.class)*/;
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ?
                factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    public JRDataSource getSession(){
        return (JRDataSource) sessionFactory.openSession();
    }
}