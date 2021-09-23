package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.domain.Job;
import sample.data.jpa.service.JobDao;

@Controller
public class JobController {

    @Autowired
    private JobDao jobDao;

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
     * GET /getsalaire  --> Get a job salaire by id
     */
    @RequestMapping(value = "/getsalaire/{id}", method = RequestMethod.GET)
    @ResponseBody
    public int getSalaireById(@PathVariable("id") Long id) {
        int salaire = 0;
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
    public String updateJobSalaire(@PathVariable("id") Long id, @PathVariable("salaire") double sal) {//Le POST gere automatiquement le parametrage
        int salaire;
        String jobName;
        try {
            Job job = jobDao.findJobById(id);
            salaire = job.getSalaires();
            jobName = job.getName();
        } catch (Exception ex) {
            return "Update failed";
        }
        return "Le salaire du métier "+jobName+" passe à "+salaire+"€";
    }
    
    
    /**
     * GET /updateJob  --> Update job salaire by id
     *///A COMMENTER. JUSTE POUR PEUPLER LA BDD
    /*@RequestMapping(value = "/createJob", method = RequestMethod.GET)
    @ResponseBody
    public String CreateUser() {//Le POST gere automatiquement le parametrage
        try {
            Job job = new Job("Developpeur",4000);
            jobDao.save(job);
        } catch (Exception ex) {
            return "Update create job";
        }
        return "";
    }*/
}
