package be.cm.order.dto;

import java.time.LocalDate;

public class ItemGroupDto {

    private String itemName;
    private int amount;
    private LocalDate shippingDate;
    private double totalPrice;

    public ItemGroupDto(String itemName, int amount, LocalDate shippingDate, double totalPrice) {
        this.itemName = itemName;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.totalPrice = totalPrice;
    }

    private ItemGroupDto() {
        // for Jackson
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
