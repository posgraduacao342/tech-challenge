package api.techchallenge.infrastructure.db.repositories;

import api.techchallenge.domain.enums.Categoria;
import api.techchallenge.infrastructure.db.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
    List<ProdutoEntity> findByCategoria(Categoria categoria);

    List<ProdutoEntity> findByIdIn(List<UUID> ids);
}
