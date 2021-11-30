package Hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "order")
public class Order {
@Id
    private String oid;
    private String date;
    private String customerID;

    @ManyToMany
    private List<Item> itemList = new ArrayList<>();

    public Order() {
    }

    public Order(String oid, String date, String customerID, List<Item> itemList) {
        this.setOid(oid);
        this.setDate(date);
        this.setCustomerID(customerID);
        this.setItemList(itemList);
    }
    @Id
    @GeneratedValue
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @ManyToMany(mappedBy = "orderList")
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
