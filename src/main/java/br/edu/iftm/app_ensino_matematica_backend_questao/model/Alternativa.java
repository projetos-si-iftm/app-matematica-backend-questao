package br.edu.iftm.app_ensino_matematica_backend_questao.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alternativa {
    private String resposta;
    private boolean correta;
}
