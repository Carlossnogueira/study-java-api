package br.com.webapi.gestao_vagas.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.webapi.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.webapi.gestao_vagas.modules.company.useCases.AuthCompanyUserCase;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {
    

    @Autowired
    private AuthCompanyUserCase authCompanyUserCase;

    @PostMapping("/Company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.authCompanyUserCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
