package sample.data.jpa.service;
import org.springframework.data.jpa.repository.JpaRepository;
import sample.data.jpa.domain.*;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    public User findUserById(Long id);

}