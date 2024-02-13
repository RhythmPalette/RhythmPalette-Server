package com.umc.Palette.domain.user.repository;

import com.umc.Palette.domain.user.Role;
import com.umc.Palette.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
    Boolean existsByLoginId(String loginId);

    User findByEmail(String email);

    Boolean existsByEmail(String email);
    Optional<User> findByLoginId(String loginId); // jwt에서 사용

    User findByRole(Role role);

}
