package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zut.wi.streamingservice.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserById(UUID uuid);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}
