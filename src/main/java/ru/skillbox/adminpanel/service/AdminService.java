package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.request.AddAdminRq;
import ru.skillbox.adminpanel.dto.response.AdminRs;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.entity.Admin;
import ru.skillbox.adminpanel.mapper.AdminMapper;
import ru.skillbox.adminpanel.repository.AdminRepository;

import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final StatisticsService statisticsService;

    public String buildAdminPage(Model model, String token) {
        model.addAttribute("admins", "router-link-active");
        model.addAttribute("adminsList", getAdmins(token));
        return "admins";
    }

    public void addAdmin(AddAdminRq addAdminRq) {

        Optional<Admin> adminOptional = adminRepository.findByAdminLoginIgnoreCase(addAdminRq.getEmail());

        if (adminOptional.isPresent()) {
            Admin existingAdmin = adminOptional.get();
            existingAdmin.setRole(addAdminRq.getRole());
            adminRepository.save(existingAdmin);
        } else {
            Admin newAdmin = new Admin();
            newAdmin.setAdminLogin(addAdminRq.getEmail());
            newAdmin.setPassword(getEncodedPassword(addAdminRq.getPassword()));
            newAdmin.setRole(addAdminRq.getRole());
            adminRepository.save(newAdmin);
        }
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    private List<AdminRs> getAdmins(String token) {
        CurrentUserInfoRs currentUser = statisticsService.getCurrentUser(token);
        List<Admin> admins = adminRepository.findByAdminLoginNotIgnoreCase(currentUser.getName());
        return adminMapper.toAdminRsList(admins).stream()
                .sorted(Comparator.comparing(AdminRs::getId))
                .toList();
    }

    public String getEncodedPassword(String password) {
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        return new String(encodedBytes);
    }
}
