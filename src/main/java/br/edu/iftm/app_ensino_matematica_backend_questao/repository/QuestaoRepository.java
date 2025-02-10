package br.edu.iftm.app_ensino_matematica_backend_questao.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Questao;
import java.util.List;



@Repository
public interface QuestaoRepository extends MongoRepository<Questao, UUID> {
    @Query("{ 'categoria.id_categoria': ?0 }")
    List<Questao> findByCategoriaId(UUID id_categoria);

    @Query("{ 'categoria.id_categoria': ?0, 'dificuldade': ?1 }")
    List<Questao> findByCategoriaIdAndDificuldade(UUID id_categoria, int dificuldade);

        @Aggregation(pipeline = {
        "{ '$match': { 'categoria.id_categoria': ?0, 'dificuldade': ?1 } }",
        "{ '$sample': { 'size': 5 } }"
    })
    List<Questao> findRandomByCategoriaIdAndDificuldade(UUID id_categoria, int dificuldade);
}
