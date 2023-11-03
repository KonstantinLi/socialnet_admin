package ru.skillbox.adminpanel.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.entity.PostComment;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostCommentsRepository extends JpaRepository<PostComment, Long> {
    Optional<PostComment> findByIdAndIsDeleted(long id, boolean isDeleted);

    Optional<PostComment> findByIdAndPostIdAndIsDeleted(long id, long postId, boolean isDeleted);

    long countByPostIdAndIsDeleted(
            long postId, boolean isDeleted
    );

    List<PostComment> findAllByPostIdAndIsDeleted(
            long postId, boolean isDeleted, Pageable pageable
    );
}
