package jpabook.jpashop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {


    @GetMapping("/bye")
    public String sayBye(String name) {
        return "bye " + name;
    }
}
