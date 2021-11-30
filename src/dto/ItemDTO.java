package dto;

import java.math.BigDecimal;

public class ItemDTO {
     private String code;
     private String description;
     private String packSize;
     private BigDecimal unitPrice;
     private int qtyOnHand;

    public ItemDTO(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
    }

    public ItemDTO(String code, String description, String packSize, BigDecimal unitPrice, int qtyOnHand) {
        this.setCode(code);
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setUnitPrice(unitPrice);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
