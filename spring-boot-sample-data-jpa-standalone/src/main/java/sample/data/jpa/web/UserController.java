package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                         @RequestParam(value = "dateNaissance", required = false) Date dateNaissance
        ) {
        String userId = "";
        try {
            User user = new User(username,dateNaissance);
            userDao.save(user);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
       // return "User succesfully created with id = " + userId;
        return "redirect:/findallusers";
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


    @RequestMapping(value = "/findallusers", method = RequestMethod.GET)
    public String findAllUsers(Model model) {
        model.addAttribute("listUsers",this.userDao.findAll());
        return "userList";
    }
    /**
     * GET /delete  --> Delete the user having the passed id.
     */
    @RequestMapping(value = "/deleteuser", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@RequestParam(value = "id", required = false) Long id) {
        try {
            userDao.delete(userDao.findUserById(id));
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
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