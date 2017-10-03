package com.zeta.Repositories;

import com.zeta.Models.Campus;
import com.zeta.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    //Using Queries: https://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html'
    @Query(value = "SELECT u FROM User WHERE u.SFU_ID = ?1", nativeQuery = true)
    User findBySfuId(String sfuid);
    @Query(value = "SELECT u FROM User WHERE u.Email = ?1", nativeQuery = true)
    User findByEmail(String email);
}