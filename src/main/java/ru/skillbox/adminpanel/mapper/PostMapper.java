package ru.skillbox.adminpanel.mapper;

import org.mapstruct.*;
import ru.skillbox.adminpanel.dto.request.PostRq;
import ru.skillbox.adminpanel.dto.response.PostRs;
import ru.skillbox.adminpanel.entity.Post;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                CommentMapper.class,
                TagMapper.class,
                PersonMapper.class
        }
)
public interface PostMapper {

    @Mapping(source = "time", target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    PostRs postToPostRs(Post post);

    List<PostRs> listPostToListPostRs(List<Post> posts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post postRqToPost(PostRq postRq, @MappingTarget Post post);
}
