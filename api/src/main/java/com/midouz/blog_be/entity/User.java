package com.midouz.blog_be.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "first_name")
    @Nonnull
    private String firstName;
    @Column(name = "last_name")
    @Nonnull
    private String lastName;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    @Nonnull
    @Column(name = "created_time")
    private Instant createdTime;
    @Nonnull
    @Column(name = "last_logging_time")
    private Instant lastLoggingTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
