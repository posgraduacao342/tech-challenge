package api.techchallenge.application.requests.produto;

import api.techchallenge.domain.core.enums.Categoria;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;


@Data
public class AtualizarProdutoRequest {
    @Nullable
    public String nome;
    @Nullable
    public BigDecimal preco;
    @Nullable
    public String imagem;
    @Nullable
    public String descricao;
    @Nullable
    public Categoria categoria;
}
