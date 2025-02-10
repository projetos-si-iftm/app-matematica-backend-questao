package br.edu.iftm.app_ensino_matematica_backend_questao.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questao")
public class Questao {
    @Id
    private UUID id_questao;
    private String titulo;
    private String enunciado;
    private String imagem;
    private int dificuldade; //1 = fácil, 2 = médio, 3 = difícil
    private List<Alternativa> alternativa;
    private String resposta_correta; 
    private List<Categoria> categoria;


}
