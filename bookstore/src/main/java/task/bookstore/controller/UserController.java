package task.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import task.bookstore.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/block/{id}")
    public String blockUser(@PathVariable(name = "id") int id) {
        userService.blockUser(id);
        return "redirect:/";
    }

    @GetMapping("/unblock/{id}")
    public String unblockUser(@PathVariable(name = "id") int id) {
        userService.unblockUser(id);
        return "redirect:/";
    }
}
