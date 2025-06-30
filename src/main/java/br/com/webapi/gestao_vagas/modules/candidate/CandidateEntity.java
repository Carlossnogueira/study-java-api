package br.com.webapi.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @NotBlank(message= "O nome não pode ficar vazio!")
    @Pattern(regexp = "\\S+", message = "A campo username não pode conter espaços!")
    private String userame;
    
    @Email(message = "O campo precisa de um email válido!")
    private String email;
    @Length(min = 5, max = 10)
    private String password;
    private String curriculum;

}
