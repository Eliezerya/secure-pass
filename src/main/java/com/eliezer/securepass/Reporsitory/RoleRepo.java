package com.eliezer.securepass.Reporsitory;
import com.eliezer.securepass.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    List<Role> findByIdRole(int idRole);
}
