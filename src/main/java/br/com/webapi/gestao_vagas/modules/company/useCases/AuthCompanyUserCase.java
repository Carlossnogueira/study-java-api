package br.com.webapi.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.webapi.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.webapi.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUserCase {
    
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Company not found");
            });

            var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

            if(!passwordMatches){
                throw  new AuthenticationException();
            }

            Algorithm algorithm = Algorithm.HMAC256("");
            var token = JWT.create().withIssuer(secretKey)
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .sign(algorithm);
            return token;
    }

}
