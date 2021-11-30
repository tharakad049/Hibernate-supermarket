package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public  ArrayList<CustomDTO> getOrderDetailsFromOrderID(String oid) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> allData= new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT o.OrderId,o.OrderDate,o.CustomerId,od.OrderId,od.Code,od.Qty,od.UnitPrice from `Orders` o inner join `OrderDetails` od on o.OrderId=od.OrderId where o.OrderId=?;", oid);
        while (rst.next()) {
            allData.add(new CustomDTO(rst.getString("orderId"), LocalDate.parse(rst.getString("orderDate")), rst.getString("customerID"), rst.getString("itemCode"), rst.getInt("qty"), rst.getBigDecimal("unitPrice")));
        }
        return allData;
    }
}
