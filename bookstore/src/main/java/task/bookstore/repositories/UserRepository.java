package task.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.bookstore.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);

    User findOneByEmail(String email);

    Optional<User> findById(int id);
}
