package br.com.webapi.gestao_vagas.modules.candidate;

import java.util.UUID;

import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;
    private String userame;
    private String email;
    private String password;
    private String curriculum;

}
