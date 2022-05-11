package com.example.usedtransactionservice.UsedTransactionService.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/search")
public class UsedTransactionServiceController {

    @GetMapping(value = "/home")
    public String homepage() {
        return "home";
    }

}
