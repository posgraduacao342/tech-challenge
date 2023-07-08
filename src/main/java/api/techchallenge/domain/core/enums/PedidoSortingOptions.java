package api.techchallenge.domain.core.enums;

public enum PedidoSortingOptions {
    DATA_RECEBIMENTO("dataRecebimento");

    private String string;

    PedidoSortingOptions(String str){
        this.string = str;
    }

    public String getString() {
        return string;
    }
}
