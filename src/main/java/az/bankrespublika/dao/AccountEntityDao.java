package az.bankrespublika.dao;

import az.bankrespublika.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityDao extends JpaRepository<AccountEntity, Long> {
    @Query("SELECT a FROM AccountEntity a WHERE a.userEntity.username = ?1")
    AccountEntity findAccountByUsername(String username);


}