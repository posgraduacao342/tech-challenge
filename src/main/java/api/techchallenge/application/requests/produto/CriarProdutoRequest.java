package api.techchallenge.application.requests.produto;

import api.techchallenge.domain.core.enums.CategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CriarProdutoRequest {
    @NotBlank
    public String nome;
    public BigDecimal preco;
    public String imagem;
    public String descricao;
    public CategoriaEnum categoria;
}
