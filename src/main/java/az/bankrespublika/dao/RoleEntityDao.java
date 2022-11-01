package az.bankrespublika.dao;

import az.bankrespublika.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityDao extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(String name);
}