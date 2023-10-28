package ru.skillbox.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.entity.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
}
