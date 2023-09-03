package api.techchallenge.infrastructure.mp;

import lombok.Data;

@Data
public class ItemMP {
    private String sku_number;
    private String title;
    private int quantity;
    private String unit_measure;
    private Double unit_price;
    private Double total_amount;

    public ItemMP(String sku_number, String title, int quantity, Double unit_price, Double total_amount) {
        this.sku_number = sku_number;
        this.title = title;
        this.quantity = quantity;
        this.unit_measure = "unit";
        this.unit_price = unit_price;
        this.total_amount = total_amount;
    }
}
