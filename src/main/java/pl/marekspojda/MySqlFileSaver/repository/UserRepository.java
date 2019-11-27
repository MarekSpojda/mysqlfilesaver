package pl.marekspojda.MySqlFileSaver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.marekspojda.MySqlFileSaver.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u  left join fetch u.files where u.email=?1")
  User findUserByEmailCustom(String email);

  Optional<User> findUserByEmail(String email);

  boolean existsByEmail(String email);
}