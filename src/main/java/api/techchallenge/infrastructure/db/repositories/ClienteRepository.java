package api.techchallenge.infrastructure.db.repositories;

import api.techchallenge.infrastructure.db.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByCpf(String cpf);

    @Query("SELECT c FROM ClienteEntity c WHERE c.cpf = :cpf OR c.email = :email")
    Optional<ClienteEntity> findByCpfOuEmail(@Param("cpf") String cpf, @Param("email") String email);

}
