package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/")
    public String createAccount(Model model){
        model.addAttribute("customer", new Customer());
        return "customerform";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("atms", atmRepository.findAll());
        return "list";
    }

   /* @RequestMapping("/deposit/{id}")
    public String depositAtm(@PathVariable("id") long id, Model model, Atm atm){
        model.addAttribute("atms", atmRepository.findAll());
        depositMoney();
        return "deposit";
    }*/

    //new try
    @RequestMapping("/deposit/{id}")
    public String depositMoney(@PathVariable("id") long id, @RequestParam("depositedAmount") long depositAmount, @ModelAttribute Atm atm, @ModelAttribute Customer customer,  Model model){

        long newMoneyAmount =  customer.getAmountInAccount() - atm.getAmount();
        model.addAttribute("atms", atmRepository.findAll());
        customer.setAmountInAccount(newMoneyAmount);
        return "deposit";
    }

    @RequestMapping(value="/transaction", method=RequestMethod.POST)
    public long depositTransaction(@RequestParam("depositedAmount") long depositedAmount ){
        return depositedAmount;
    }



    @GetMapping("/transaction")
    public String createTransaction(Model model){
        model.addAttribute("atm", new Atm());
        return "transactionform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Atm atm, BindingResult result){
        if(result.hasErrors()){
            return "transactionform";
        }
        atmRepository.save(atm);
        return "redirect:/list";
    }

    @PostMapping("/process2")
    public String processForm2(@Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "customerform";
        }
        customerRepository.save(customer);
        return "redirect:/transaction";
    }

    @RequestMapping("/update/{id}")
    public String withdrawAtm(@PathVariable("id") long id, Model model){
        model.addAttribute("atm", atmRepository.findById(id));
        return "transactionform";
    }




}

