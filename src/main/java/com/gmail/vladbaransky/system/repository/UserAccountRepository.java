package com.gmail.vladbaransky.system.repository;

import com.gmail.vladbaransky.system.repository.model.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    List<UserAccount> findAll();

    UserAccount findAllById(Long id);

    UserAccount save(UserAccount userAccount);

    @Query(value = "select * from users.user where username=:username", nativeQuery = true)
    UserAccount findByUsername(@Param("username") String username);
}
