package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    //public String mapUsersToPage(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {
    public String mapUsersToPage(ModelMap model) {
        model.addAttribute("userlist", userService.getAllUsers());
        return "users";
    }
    @GetMapping("/{id}")
    public String mapUserToEditPage(@PathVariable Long id, ModelMap model) {
        //@RequestParam(value = "id", required = true) Integer id
        User user = new User();
        user.setId(id.longValue());
        model.addAttribute("user", userService.getUser(user));
        return "edit";
    }
}
