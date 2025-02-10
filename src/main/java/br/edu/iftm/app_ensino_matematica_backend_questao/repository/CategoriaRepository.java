package br.edu.iftm.app_ensino_matematica_backend_questao.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;

@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, UUID> {
    
}
