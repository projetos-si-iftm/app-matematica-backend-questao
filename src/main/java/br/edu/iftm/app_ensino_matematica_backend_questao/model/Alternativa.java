package br.edu.iftm.app_ensino_matematica_backend_questao.model;


import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alternativa")
public class Alternativa {
    @Id
    private UUID id_alternativa;
    private String resposta;
    private boolean correta;
}
