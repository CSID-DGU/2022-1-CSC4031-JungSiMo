package com.example.usedtransactionservice.UsedTransactionService.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UsedTransactionServiceController {

    @GetMapping(value = "/home")
    public String homepage() {
        return "home";
    }

}
