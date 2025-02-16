package br.edu.iftm.app_ensino_matematica_backend_questao.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questao")
public class Questao {
    @Id
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.STRING)
    private UUID id_questao;
    private String titulo;
    private String enunciado;
    private String imagem;
    private int dificuldade; //1 = fácil, 2 = médio, 3 = difícil
    private List<Alternativa> alternativa;
    @DBRef
    private List<Categoria> categoria;


}
