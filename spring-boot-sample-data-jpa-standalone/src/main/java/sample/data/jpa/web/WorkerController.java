package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.data.jpa.domain.Job;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.JobDao;
import sample.data.jpa.service.WorkerDao;

import java.sql.Date;

@Controller
public class WorkerController {

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private JobDao jobDao;

    @RequestMapping(value = "/findallworkers", method = RequestMethod.GET)
    public String findAllWorkers(Model model) {
        model.addAttribute("listworkers",this.workerDao.findAll());
        return "workerList";
    }

    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/createworker", method = RequestMethod.POST)
    public String create(@RequestParam(value = "workername", required = true) String workername,
                         @RequestParam(value = "dateNaissance", required = true) Date dateNaissance,
                         @RequestParam(value = "jobname",required = true) String jobName,
                         @RequestParam(value = "rate",required = true)int rate,
                         RedirectAttributes redirectAttributes
    ) {
        try {
            Job job = jobDao.findJobByName(jobName);
            Worker worker = new Worker(workername,dateNaissance,job,rate);
            workerDao.save(worker);
        } catch (Exception ex) {
            return "Error creating the worker: " + ex.toString();
        }
        // return "User succesfully created with id = " + userId;
        return "redirect:/findallworkers";
    }


    @GetMapping("/deleteworker/{id}")
    //public String delete(@PathVariable("id") long id, Model model) {
    public String delete(@PathVariable("id") long id, Model model) {
        Worker worker = workerDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        workerDao.delete(worker);
        return "redirect:/findallworkers";
    }





}
