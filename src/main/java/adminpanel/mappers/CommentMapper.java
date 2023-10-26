package adminpanel.mappers;

import adminpanel.api.response.CommentRs;
import adminpanel.model.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostMapperMethods.class})
public interface CommentMapper {

    @Mapping(target = "time", source = "time", qualifiedByName = "getPostTime")
    CommentRs toCommentRs(CommentEntity comment);
}
