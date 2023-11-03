package ru.skillbox.adminpanel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.adminpanel.entity.Tag;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface TagMapper {
    default String tagToString(Tag tag) {
        return tag.getTag();
    }

    default Tag stringToTag(String string) {
        Tag tag = new Tag();
        tag.setTag(string);
        return tag;
    }
}
