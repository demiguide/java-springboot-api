package com.springtool.demiguide.service;

import com.springtool.demiguide.entity.UserEntity;
import com.springtool.demiguide.repository.NativeUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NativeUserService {
    @Autowired
    private NativeUserRepository repo;
    @Autowired
    private EntityManager entityManager;


    public List<UserEntity> getAllUser(){
        return repo.findAllUser();
    }

    public Optional<UserEntity> getUserById(Long id){
        return repo.findUserById(id);
    }

    public Optional<UserEntity> addUser(UserEntity user){
        int flag = repo.addUser(user.getName(), user.getEmail(), user.getPassword());
        return flag > 0 ? repo.findUserByName(user.getName()) : Optional.empty();
    }

    @Transactional
    public Optional<UserEntity> updateUser(Long id, Map<String, Object> fields) {
        if (fields == null || fields.isEmpty()) return Optional.empty();

        Set<String> allowedFields = Set.of("name", "email", "password");
        //JPQL (Java Persistence Query Language) use Entity class name not table name in database
        StringBuilder jpql = new StringBuilder("UPDATE UserEntity u SET ");
        Map<String, Object> params = new HashMap<>();

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String key = entry.getKey();
            if (!allowedFields.contains(key)) continue;

            jpql.append("u.").append(key).append(" = :").append(key).append(", ");
            params.put(key, entry.getValue());
        }

        if (params.isEmpty()) return Optional.empty(); // ไม่มี field ที่อนุญาต

        jpql.delete(jpql.length() - 2, jpql.length());
        jpql.append(" WHERE u.id = :id");
        params.put("id", id);

        Query query = entityManager.createQuery(jpql.toString());
        params.forEach(query::setParameter);

        return query.executeUpdate() > 0
                ? repo.findUserById(id)
                : Optional.empty();
    }

    public Boolean deleteUser(Long id){
        int flag = repo.deleteUser(id);
        return flag > 0;
    }
}
