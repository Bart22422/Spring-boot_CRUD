package web.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.User;


public interface UserRep extends CrudRepository<User, Long> {
    User findAllById(Long id);
}
