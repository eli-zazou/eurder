package be.cm.item.dto;

public class CreateItemDto {

    String name;
    String description;
    double price;
    int amount;

    private CreateItemDto(){
        // for Jackson
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
