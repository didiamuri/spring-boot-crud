package cd.vodacom.springbootcrud.repository;

import cd.vodacom.springbootcrud.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
