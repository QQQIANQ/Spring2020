package sample.data.jpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexConroller {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/workerUpdate")
    public String workerUpdate(){return "redirect:/getalljobs";}

}
