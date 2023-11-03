package ru.skillbox.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.entity.Post2Tag;

@Repository
public interface Post2tagRepository extends JpaRepository<Post2Tag, Long> {
    long countByPostId(long postId);
}
