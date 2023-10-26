package adminpanel.repositories;

import adminpanel.api.response.RegionStatisticRs;
import adminpanel.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {

    Optional<PersonEntity> findByEmail(String email);

    @Query(value = "SELECT new adminpanel.api.response.RegionStatisticRs(p.city, COUNT(p.city)) FROM PersonEntity AS p " +
            "WHERE p.city IS NOT NULL " +
            "GROUP BY p.city")
    List<RegionStatisticRs> getCityWithUsersCount();

    @Query(value = "SELECT new adminpanel.api.response.RegionStatisticRs(p.country, COUNT(p.country)) FROM PersonEntity AS p " +
            "WHERE p.country IS NOT NULL " +
            "GROUP BY p.country")
    List<RegionStatisticRs> getCountryWithUsersCount();

    List<PersonEntity> findPersonByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName);

    List<PersonEntity> findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(String firstName, String lastName);
}
