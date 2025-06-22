package com.springtool.demiguide.repository;

import com.springtool.demiguide.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NativeUserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserEntity> findAllUser();

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<UserEntity> findUserById(@Param("id") Long id);

    @Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
    Optional<UserEntity> findUserByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (name, email, password) VALUES(:name, :email, :password)", nativeQuery = true)
    int addUser(@Param("name") String name, @Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = :name, email = :email, password = :password WHERE id = :id", nativeQuery = true)
    int updateUser(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    int deleteUser(@Param("id") Long id);
}
