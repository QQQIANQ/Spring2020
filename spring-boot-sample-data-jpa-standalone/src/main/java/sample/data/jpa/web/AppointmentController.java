package sample.data.jpa.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;
import sample.data.jpa.service.UserDao;
import sample.data.jpa.service.WorkerDao;

@Controller
public class AppointmentController {
	
	@Autowired
    private AppointmentDao appointmentDao;
	@Autowired
    private UserDao userDao;
	@Autowired
    private WorkerDao workerDao;
	
	@RequestMapping(value = "/findallappointments", method = RequestMethod.GET)
    public String findAllAppointments(Model model) {
        model.addAttribute("listAppointments",this.appointmentDao.findAll());
        return "appointmentList";
    }
	
	
	@RequestMapping(value = "/createappointment", method = RequestMethod.GET)
    public String createAppointmentGET(Model model) {
		model.addAttribute("listUsers",this.userDao.findAll());
		model.addAttribute("listWorkers",this.workerDao.findAll());
		System.out.println(this.userDao.findAll().size());
        return "createAppointment";
    }
	
	
	@RequestMapping(value = "/createappointment", method = RequestMethod.POST)
    public String createAppointmentPOST(
    			@RequestParam(value = "sujet") String sujet,
                @RequestParam(value = "dateRDV") Date dateRDV,
                @RequestParam(value = "userID") Long userId,
                @RequestParam(value = "workerID") Long workerID
        ) {
        try {
            User user = this.userDao.getOne(userId);
            Worker worker = this.workerDao.getOne(workerID);

            Appointment appointment= new Appointment(sujet,dateRDV,user,worker);
            appointmentDao.save(appointment);
        } catch (Exception ex) {
            return "Error creating the appointment: " + ex.toString();
        }
       return "Appointment succesfully created";
    }

	
}
