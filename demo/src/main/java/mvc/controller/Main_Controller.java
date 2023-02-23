package mvc.controller;


import mvc.repository.ProgrammerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class Main_Controller {

    @Autowired
    ProgrammerRepo pr;



    @GetMapping("/home")
    public String homepage(){
        return "homePage.html";
    }
    @PostMapping ( "/addProgrammer")
    public String addProgrammer(@ModelAttribute Programmer programmer){
        pr.save(programmer);
        return "redirect:/home";
    }
//    @PostMapping ( "/findByLang")
//    public String findById(@RequestParam int pId,Model m){
//       Programmer p=pr.findById(pId).orElse(null);
//       m.addAttribute("programmer",p);
//        return "ProgrammerInfo.html";
//    }

    @PostMapping ( "/findByLang")
    public String findById(@RequestParam int pId,Model m){
        Programmer p=pr.findById(pId).orElse(null);
        m.addAttribute("programmer",p);
        return "ProgrammerInfo.html";
    }

    @PostMapping ( "/updateProgrammer")
    public String updateProgrammer(@ModelAttribute Programmer programmer){
        Programmer p=pr.findById(programmer.getpId()).orElse(null);
        p.setpName(programmer.getpName());
        p.setpLang(programmer.getpLang());
        pr.save(p);
        return "ProgrammerInfo.html";
    }
    @GetMapping ( "/deleteProgrammer")
    public String deleteProgrammer(@RequestParam int pId){
        pr.deleteById(pId);
        return "redirect:/home";
    }

}
