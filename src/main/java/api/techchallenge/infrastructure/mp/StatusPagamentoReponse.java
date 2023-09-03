package api.techchallenge.infrastructure.mp;

import lombok.Data;

@Data
public class StatusPagamentoReponse {
    public String status;
    public String external_reference;
}
