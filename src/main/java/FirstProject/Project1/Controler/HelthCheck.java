package FirstProject.Project1.Controler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelthCheck {

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "Ok";
    }
}
