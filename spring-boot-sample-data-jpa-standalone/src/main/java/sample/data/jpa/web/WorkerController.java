package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.data.jpa.service.UserDao;
import sample.data.jpa.service.WorkerDao;

@Controller
public class WorkerController {

    @Autowired
    private WorkerDao workerDao;

    @RequestMapping(value = "/findallworkers", method = RequestMethod.GET)
    public String findAllWorkers(Model model) {
        model.addAttribute("listworkers",this.workerDao.findAll());
        return "workerList";
    }
}
