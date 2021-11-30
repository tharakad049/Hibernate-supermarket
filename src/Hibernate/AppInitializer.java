package Hibernate;

import Hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;;

public class AppInitializer {
    public static void main(String[] args) {

/*        Customer customer = new Customer();
        customer.setId("C-001");
        customer.setTitle("Male");
        customer.setName("Dilan");
        customer.setAddress("Katharagama");
        customer.setCity("Katharagama");
        customer.setProvince("UVA");
        customer.setPostalCode("91400");*/

/*        Item item = new Item();
        item.setItemCode("I-001");
        item.setDescription("Bundel");
        item.setPackSize("Packet");
        item.setQtyOnHand(500);
        item.setUnitPrice(66.89);*/

/*
        Item item1 = new Item();
        item1.setCode("I-002");
        item1.setDescription("Bundel");
        item1.setPackSize("Packet");
        item1.setQtyOnHand(500);
        item1.setUnitPrice(66.89);*/

/*        Order order = new Order();
        order.setOid("O-001");
        order.setDate("2021-12-13");
        order.setCustomerID("C-001");*/


/*        item.getOrderList().add(order);
        item1.getOrderList().add(order);

        order.getItemList().add(item);
        order.getItemList().add(item1);*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        //session.save(customer);
        //session.save(item);
        //session.save(item1);
        //session.save(order);

        transaction.commit();
        session.close();
    }
}