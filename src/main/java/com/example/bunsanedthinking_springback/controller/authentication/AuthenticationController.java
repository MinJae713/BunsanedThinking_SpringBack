package com.example.bunsanedthinking_springback.controller.authentication;

import com.example.bunsanedthinking_springback.dto.authentication.LoginResponse;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/loginCustomer")
    public LoginResponse loginCustomer(@RequestParam int id) throws NotExistException {
        return authenticationService.loginCustomer(id);
    }

    @GetMapping("/loginEmployee")
    public LoginResponse loginEmployee(@RequestParam int id) throws NotExistException {
        return authenticationService.loginEmployee(id);
    }

    @GetMapping("/loginPartnerCompany")
    public LoginResponse loginPartnerCompany(@RequestParam int id) throws NotExistException {
        return authenticationService.loginPartnerCompany(id);
    }
}
