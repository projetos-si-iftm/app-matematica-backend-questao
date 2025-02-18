package br.edu.iftm.app_ensino_matematica_backend_questao.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO.CategoriaDTO;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("manage/category")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaDTO> getAll(){
        return categoriaService.getAll();
    }


    @GetMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria findById(@PathVariable UUID id_categoria){
        return categoriaService.findById(id_categoria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.save(categoriaDTO);
    }

    @DeleteMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoriaDTO delete(@PathVariable UUID id_categoria){
        return categoriaService.delete(id_categoria);
    }

    @PatchMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO update(@PathVariable UUID id_categoria, @RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.update(id_categoria, categoriaDTO);
    }


}
