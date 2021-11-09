package sample.data.jpa.web;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

import java.sql.Date;
import java.util.List;

@Controller
public class UserController {

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String create(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "dateNaissance", required = false) Date dateNaissance,
                         RedirectAttributes redirectAttributes
        ) {
        String userId = "";
        try {
            User user = new User(username,dateNaissance);
            userDao.save(user);
            userId = String.valueOf(user.getId());
            redirectAttributes.addAttribute("id",user.getId());
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
       // return "User succesfully created with id = " + userId;
        return "redirect:/userinfo";
    }



    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    @ResponseBody
    public String getNameById(@RequestParam(value = "id", required = false) Long id) {
        String userName = "";
        try {
            User user = userDao.findUserById(id);
            userName = user.getName();
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user name is: " + userName;
    }


    @RequestMapping(value = "/userinfo")
    public String userInfo(@RequestParam(value = "id", required = false) Long id,Model model) {
        model.addAttribute("u",this.userDao.findUserById(id));
        return "userInfo";
    }

    @RequestMapping(value = "/findallusers", method = RequestMethod.GET)
    public String findAllUsers(Model model) {
        model.addAttribute("listUsers",this.userDao.findAll());
        return "userList";
    }
    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @GetMapping("/deleteuser/{id}")
    //public String delete(@PathVariable("id") long id, Model model) {
    public String delete(@PathVariable("id") long id, Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userDao.delete(user);
        return "redirect:/";
    }

    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @GetMapping("/deleteuseradmin/{id}")
    public String deleteAdmin(@PathVariable("id") long id, Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userDao.delete(user);
        return "redirect:/findallusers";
    }


    /**
     * POST /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        try {
            userDao.save(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // Private fields

    @Autowired
    private UserDao userDao;

}