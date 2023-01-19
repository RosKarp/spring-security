package spring.security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.security.services.UserService;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    @GetMapping("/all_users")
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "Admins_page";
    }
}
