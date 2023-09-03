package api.techchallenge.infrastructure.mp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoMP {
    private String external_reference;
    private Double total_amount;
    private List<ItemMP> items;
    private String title;
    private String expiration_date;
    private String notification_url;
    private String description;

    public PedidoMP(String external_reference, Double total_amount, String expiration_date, String notification_url) {
        this.title = "Pedido da lanchonete";
        this.external_reference = external_reference;
        this.total_amount = total_amount;
        this.expiration_date = expiration_date;
        this.notification_url = notification_url;
        this.items = new ArrayList<>();
        this.description =  "";
    }

    public void adicionarItem(ItemMP item){
        var listaItem = this.getItems();
        listaItem.add(item);
    }
}