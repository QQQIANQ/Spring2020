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
     * POST /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/createjob", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Job job) {
        String jobId = "";
        try {
            jobDao.save(job);
            jobId = String.valueOf(job.getId());
        } catch (Exception ex) {
            return "Error creating the job: " + ex.toString();
        }
        return "Job succesfully created with id = " + jobId;
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
     * DELETE /delete  --> Delete the user having the passed id.
     */
    @RequestMapping(value = "/deletejob/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        try {
            jobDao.delete(jobDao.findJobById(id));
        } catch (Exception ex) {
            return "Error deleting the job:" + ex.toString();
        }
        return "Job succesfully deleted!";
    }


}
