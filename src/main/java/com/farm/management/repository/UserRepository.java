package com.farm.management.repository;

import com.farm.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select * from tb_users where created_by = :created_by", nativeQuery = true)
    List<User> findByCreated_by(@Param("created_by") Long created_by);
    
    @Modifying
	@Transactional
    @Query(value = "UPDATE tb_users SET password = :newPassword WHERE id = :userId", nativeQuery = true)
    void changePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);
}
