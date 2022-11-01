package az.bankrespublika.security;

import az.bankrespublika.dao.UserEntityDao;
import az.bankrespublika.entity.UserEntity;
import az.bankrespublika.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserEntityDao userEntityDao;

    @Autowired
    public JwtUserDetailsService(@Lazy UserEntityDao userEntityDao) {
        this.userEntityDao = userEntityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityDao.findUserEntityByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return JwtUserFactory.create(user);
    }


}