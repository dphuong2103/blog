package com.midouz.blog_be.repository;

import com.midouz.blog_be.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    Optional<UserToken> findUserTokenByTokenValue(String token);
}
