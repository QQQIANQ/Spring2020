package sample.data.jpa.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.AppointmentDao;
import sample.data.jpa.service.UserDao;

@Controller
public class AppointmentController {
	
	@Autowired
    private AppointmentDao appointmentDao;
	
	
	@RequestMapping(value = "/findallappointments", method = RequestMethod.GET)
    public String findAllAppointments(Model model) {
        model.addAttribute("listAppointments",this.appointmentDao.findAll());
        return "appointmentList";
    }
	
	
	@RequestMapping(value = "/createappointment", method = RequestMethod.GET)
    public String createAppointmentGET(Model model) {
        return "createAppointment";
    }
	
	
	/*@RequestMapping(value = "/createAppointment", method = RequestMethod.POST)
    public String createAppointmentPOST(@RequestParam(value = "username", required = false) String username,
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
    }*/

	
}
