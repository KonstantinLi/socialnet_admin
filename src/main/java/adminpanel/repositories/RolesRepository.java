package adminpanel.repositories;

import adminpanel.model.PersonEntity;
import adminpanel.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByPerson(PersonEntity person);

    List<RoleEntity> findAllByPersonNot(PersonEntity me);
}
