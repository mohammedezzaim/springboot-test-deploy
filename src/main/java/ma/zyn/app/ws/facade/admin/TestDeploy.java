package ma.zyn.app.ws.facade.admin;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test/deploy/")
public class TestDeploy {

    @GetMapping
    String sayHello() {
        return "the application is deploy with success";
    }
}
