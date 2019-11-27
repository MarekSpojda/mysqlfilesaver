package pl.marekspojda.MySqlFileSaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.marekspojda.MySqlFileSaver.entity.FileRepresentation;

public interface FileRepresentationRepository extends JpaRepository<FileRepresentation, Long> {
//  @Query("select u from User u where u.email=?1")
//  User findUserByEmailCustom(String email);
}