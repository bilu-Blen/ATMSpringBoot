package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AtmRepository atmRepository;

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("atms", atmRepository.findAll());
        return "list";
    }

    @GetMapping("/transaction")
    public String depositTransaction(Model model){
        model.addAttribute("atm", new Atm());
        return "transactionform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Atm atm, BindingResult result){
        if(result.hasErrors()){
            return "transactionform";
        }
        atmRepository.save(atm);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String withdrawAtm(@PathVariable("id") long id, Model model){
        model.addAttribute("atm", atmRepository.findById(id));
        return "transactionform";
    }

    @RequestMapping("/deposit/{id}")
    public String depositAtm(@PathVariable("id") long id, Model model){
        model.addAttribute("amount", atmRepository.findById(id));
        return "Deposit";
    }

}

