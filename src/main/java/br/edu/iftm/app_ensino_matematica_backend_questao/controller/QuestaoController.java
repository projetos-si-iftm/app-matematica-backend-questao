package br.edu.iftm.app_ensino_matematica_backend_questao.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO.QuestaoDTO;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.QuestaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("manage/question")
public class QuestaoController {
    private final QuestaoService questaoService;
    
    @PostMapping
    public QuestaoDTO save( @RequestBody QuestaoDTO questaoDTO){
        return questaoService.save(questaoDTO);
    }

    @GetMapping("/{id_questao}")
    public QuestaoDTO findById(@PathVariable UUID id_questao){
        return questaoService.findById(id_questao);
    }

    @GetMapping
    public List<QuestaoDTO> getAll(){
        return questaoService.getAll();
    }

    @GetMapping("/category/{id_categoria}")
    public List<QuestaoDTO> findByCategoria(@PathVariable UUID id_categoria) {
        return questaoService.findByCategoria(id_categoria);
    }

    @GetMapping("/category/dificulty")
    public List<QuestaoDTO> findByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findByCategoriaAndDificuldade(id_categoria, dificuldade);
    }

    @GetMapping("/category/dificulty/search")
    public List<QuestaoDTO> findRandomByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findRandomByCategoriaAndDificuldade(id_categoria, dificuldade);
    }
}
