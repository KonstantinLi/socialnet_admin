package adminpanel.service;

import adminpanel.api.request.AddModerRq;
import adminpanel.api.response.ModeratorsRs;
import adminpanel.mappers.RoleMapper;
import adminpanel.model.PersonEntity;
import adminpanel.model.RoleEntity;
import adminpanel.repositories.PersonsRepository;
import adminpanel.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static adminpanel.model.enums.Roles.ADMIN;
import static adminpanel.model.enums.Roles.MODER;

@Service
@RequiredArgsConstructor
public class ModeratorsService {

    private final PersonsRepository personsRepository;

    private final RolesRepository rolesRepository;

    private final RoleMapper roleMapper;

    public String buildModeratorsPage(Model model) {
        model.addAttribute("moderators", "router-link-active");
        model.addAttribute("moderatorsList", findModerators());
        return "moderators";
    }

    public void addModer(AddModerRq addModerRq) {
        Optional<PersonEntity> person = personsRepository.findByEmail(addModerRq.getEmail());
        if (person.isPresent()) {
            RoleEntity roleEntity = new RoleEntity();
            if (addModerRq.getRole().equals(MODER)) {
                roleEntity.setRole(MODER);
            } else if (addModerRq.getRole().equals(ADMIN)) {
                roleEntity.setRole(ADMIN);
            }
            roleEntity.setPerson(person.get());
            rolesRepository.save(roleEntity);
        }
    }

    public void deleteModer(Long id) {
        Optional<RoleEntity> roleEntity = rolesRepository.findById(id);
        roleEntity.ifPresent(rolesRepository::delete);
    }

    private List<ModeratorsRs> findModerators() {
        PersonEntity person = personsRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get();
        return toModeratorsRs(rolesRepository.findAllByPersonNot(person)).stream()
                .sorted(Comparator.comparing(ModeratorsRs::getId))
                .collect(Collectors.toList());
    }

    private List<ModeratorsRs> toModeratorsRs(List<RoleEntity> moderators) {
        return moderators.stream().map(roleMapper::toModeratorsRs).collect(Collectors.toList());
    }
}
