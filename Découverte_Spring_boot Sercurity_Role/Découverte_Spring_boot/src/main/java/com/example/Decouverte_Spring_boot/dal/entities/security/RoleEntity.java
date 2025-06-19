package com.example.Decouverte_Spring_boot.dal.entities.security;

import com.example.Decouverte_Spring_boot.dal.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "Role")
@Table(name = "roles", schema = "security", uniqueConstraints = {
        @UniqueConstraint(name = "UK_Role_name", columnNames = "name")
})
@Data
public class RoleEntity extends AbstractEntity implements GrantedAuthority {
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
