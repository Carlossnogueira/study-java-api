package br.com.webapi.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.webapi.gestao_vagas.modules.company.entities.JobEntity;
import br.com.webapi.gestao_vagas.modules.company.useCases.CreateJobUserCase;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
    
    @Autowired
    public CreateJobUserCase createJobUserCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity){
        return this.createJobUserCase.execute(jobEntity);
    }

}
