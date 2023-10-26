package adminpanel.mappers;

import adminpanel.api.response.PostRs;
import adminpanel.model.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostMapperMethods.class})
public interface PostMapper {

    @Mapping(target = "time", source = "time", qualifiedByName = "getPostTime")
    PostRs toPostRs(PostEntity post);
}
