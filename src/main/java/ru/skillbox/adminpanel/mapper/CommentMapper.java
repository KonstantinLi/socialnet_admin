package ru.skillbox.adminpanel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skillbox.adminpanel.dto.response.CommentRs;
import ru.skillbox.adminpanel.entity.PostComment;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                PersonMapper.class
        }
)
public interface CommentMapper {
    @Mapping(target = "postId", source = "post.id")
    CommentRs toCommentRs(PostComment postComment);
}
