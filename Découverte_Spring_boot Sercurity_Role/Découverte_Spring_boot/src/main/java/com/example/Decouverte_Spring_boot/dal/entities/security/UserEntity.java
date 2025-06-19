package com.example.Decouverte_Spring_boot.dal.entities.security;

import com.example.Decouverte_Spring_boot.dal.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "User")
@Table(
        name = "users",
        schema = "security",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_User_email", columnNames = "email")
        }
)
@Data
public class UserEntity extends AbstractEntity implements UserDetails {
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", schema = "security")
    private List<RoleEntity> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
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
        return isActive();
    }
}
