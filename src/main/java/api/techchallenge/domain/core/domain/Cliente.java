package api.techchallenge.domain.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cliente extends BaseEntity {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
}
