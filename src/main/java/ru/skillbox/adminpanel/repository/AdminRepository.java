package ru.skillbox.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.entity.Admin;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByAdminLoginNotIgnoreCase(String adminLogin);

    Optional<Admin> findByAdminLoginIgnoreCase(String email);

    @Override
    void deleteById(Long id);
}
