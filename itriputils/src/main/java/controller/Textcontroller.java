package controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hollow")
public class Textcontroller {
    @RequestMapping("/")
    public String hollow()
    {
        return "hollow jb";
    }
}
