package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/common_path")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    //public String mapUsersToPage(@RequestParam(value = "count", required = false) Integer count, ModelMap model) {
    public String listUsers(ModelMap model) {
        model.addAttribute("userlist", userService.getAllUsers());
        return "users";
    }
/*    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") Long id, ModelMap model) {
        //@RequestParam(value = "id", required = true) Integer id
        User user = new User();
        user.setId(id);
        model.addAttribute("user", userService.getUser(user));
        return "edit";
    }*/
    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id", required = true) Long id, ModelMap model) {
        /*User user = new User();
        user.setId(id);*/
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/addnew")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

/*    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(user));
        return "people/edit";
    }*/

/*    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/users";
    }*/
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

/*    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        userService.deleteUser(user);
        return "redirect:/users";
    }*/
    @PostMapping("/delete")
    //public String deleteUser(@RequestParam(value = "id", required = true) Long id) {
    public String deleteUser(@ModelAttribute("user") User user) {
        /*User user = new User();
        user.setId(id);*/
        userService.deleteUser(user);
        return "redirect:/users";
    }
}
