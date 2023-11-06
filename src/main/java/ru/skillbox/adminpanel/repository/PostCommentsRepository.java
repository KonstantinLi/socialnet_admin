package ru.skillbox.adminpanel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.entity.PostComment;


@Repository
public interface PostCommentsRepository extends JpaRepository<PostComment, Long>, JpaSpecificationExecutor<PostComment> {
}
