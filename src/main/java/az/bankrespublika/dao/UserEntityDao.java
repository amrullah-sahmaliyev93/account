package az.bankrespublika.dao;

import az.bankrespublika.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityDao extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsername(String username);

}