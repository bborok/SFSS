package com.zeta.Repositories;

import com.zeta.Models.Campus;
import com.zeta.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    //Using Queries: https://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/jpa.repositories.html'
    User findByEmail(String email);
    User findByPreferredCampus(Campus campus);
    User findByAccountCode(int accountCode);
    User findBySfuId(String sfuid);
}