package com.eliezer.securepass.Reporsitory;

import com.eliezer.securepass.Domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long id);

    List<Account> findAllByNameAccountAndUserId(String keyword,Long userId);
}
