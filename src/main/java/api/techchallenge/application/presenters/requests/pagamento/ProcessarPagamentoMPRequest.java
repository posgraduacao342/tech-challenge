package api.techchallenge.application.presenters.requests.pagamento;

import lombok.Data;

@Data
public class ProcessarPagamentoMPRequest {
    private Data data;

    @Override
    public String toString() {
        return "ProcessarPagamentoMPRequest{" +
                "data=" + data +
                '}';
    }
    @lombok.Data
    public static class Data {
        private String id;

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
