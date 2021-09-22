package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @RequestMapping(value = "/createuser",method = RequestMethod.POST)
  @ResponseBody
  public String create(@RequestBody User user) {
    String userId = "";
    try {
      userDao.save(user);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }

  @RequestMapping(value = "/get-name-by-id/{id}",method = RequestMethod.GET)
  @ResponseBody
  public String getNameById(@PathVariable("id") Long id) {
    String userName = "";
    try {
      User user = userDao.findUserById(id);
      userName = user.getName();
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user name is: " + userName;
  }

  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@PathVariable("id") Long id) {
    try {
      userDao.delete(userDao.findUserById(id));
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }

  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.

  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userDao.findById(id).get();
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  */

  // Private fields

  @Autowired
  private UserDao userDao;
  
}