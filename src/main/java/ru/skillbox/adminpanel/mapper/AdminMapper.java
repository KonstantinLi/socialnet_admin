package ru.skillbox.adminpanel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skillbox.adminpanel.dto.response.AdminRs;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.entity.Admin;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AdminMapper {
    List<AdminRs> toAdminRsList(List<Admin> adminList);

    @Mapping(target = "name", source = "adminLogin")
    CurrentUserInfoRs toCurrentUserInfoRs(Admin admin);
}
