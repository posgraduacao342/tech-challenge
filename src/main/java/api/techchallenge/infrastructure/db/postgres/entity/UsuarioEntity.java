package api.techchallenge.infrastructure.db.postgres.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    String nome;

    @Column(name = "email", unique = true)
    String userName;

    @Column(nullable = false)
    String cpf;

    @Column(nullable = false)
    String senha;

    @Column
    String descricao;

    @ManyToMany
    @JoinTable(name = "usuarios_papeis",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_papel"))
    private List<PapelEntity> papeis;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.papeis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
