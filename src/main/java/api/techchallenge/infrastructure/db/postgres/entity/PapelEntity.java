package api.techchallenge.infrastructure.db.postgres.entity;

import api.techchallenge.domain.core.enums.Papel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "papeis")
public class PapelEntity extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    Papel papel;

    @Override
    public String getAuthority() {
        return this.papel.toString();
    }
}
