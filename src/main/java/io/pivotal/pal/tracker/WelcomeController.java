package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {
    private final String hello;

    public WelcomeController(@Value("${welcome.message}") String hello) {
        this.hello = hello;
    }

    @GetMapping("/")
    public String sayHello() {
        return this.hello;
    }

    /*@GetMapping("/")
    public String WelcomeController(@Value("${welcome.message}")) {
        hello = this;
        retrn hello;
    }*/
}