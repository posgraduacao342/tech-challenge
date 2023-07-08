package api.techchallenge.domain.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cliente extends BaseEntity {
    private String nome;
    private String email;
    private String cpf;
}
