package com.midouz.blog_be.entity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")

public class User {
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
}
