package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.domain.Job;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.JobDao;

@Controller
public class JobController {

    @Autowired
    private JobDao jobDao;

    
    
    @RequestMapping("/jobUpdate")
    public String jobUpdate(Model model) {
    	model.addAttribute("listJobs",this.jobDao.findAll());
        return "jobUpdate";
    }
    
    
    /**
     * GET /getjob  --> Get a job name by id
     */
    @RequestMapping(value = "/getjob/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getJobNameById(@PathVariable("id") Long id) {
        String jobName = "";
        try {
            Job job = jobDao.findJobById(id);
            jobName = job.getName();
        } catch (Exception ex) {
            return "Job not found";
        }
        return "The job name is: " + jobName;
    }

    /**
     * GET /getalljobs  --> Get all job
     */
    @RequestMapping(value = "/getalljobs", method = RequestMethod.GET)
    public String getAllJobs(Model model) {
        model.addAttribute("listJobs",this.jobDao.findAll());
        return "jobList";
    }

    /**
     * GET /getsalaire  --> Get a job salaire by id
     */
    @RequestMapping(value = "/getsalaire/{id}", method = RequestMethod.GET)
    @ResponseBody
    public double getSalaireById(@PathVariable("id") Long id) {
        double salaire = 0;
        try {
            Job job = jobDao.findJobById(id);
            salaire = job.getSalaires();
        } catch (Exception ex) {
            return 0;
        }
        return salaire;
    }

    /**
     * POST /updateJob  --> Update job salaire by id
     */
    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    @ResponseBody
    public String updateJobSalaire(@RequestParam(value = "id") Long id, @RequestParam(value = "sal") double sal) {
        double salaire;
        String jobName;
        try {
            Job job = jobDao.findJobById(id);
            job.setSalaires(sal);
            jobDao.save(job);
            salaire = job.getSalaires();
            jobName = job.getName();
        } catch (Exception ex) {
            return "Update failed";
        }
        return "Le salaire du métier "+jobName+" passe à "+salaire+"€";
    }

    /**
     * GET /delete  --> Delete the job having the passed id.
     */
    @GetMapping("/deletejob/{id}")
    //public String delete(@PathVariable("id") long id, Model model) {
    public String delete(@PathVariable("id") long id, Model model) {
        Job job = jobDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        jobDao.delete(job);
        return "redirect:/";
    }
    
    
    /**
     * GET /updateJob  --> Update job salaire by id
     *///A COMMENTER. JUSTE POUR PEUPLER LA BDD
   /** @RequestMapping(value = "/createJob", method = RequestMethod.GET)
    @ResponseBody
    public String CreateUser() {//Le POST gere automatiquement le parametrage
        try {
            Job job = new Job("Developpeur",4000);
            jobDao.save(job);
        } catch (Exception ex) {
            return "Update create job";
        }
        return "";
    }
    **/
}
