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

import com.example.dtos.CategoriaDTO;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Categoria", description = "Gerenciamento de categorias do aplicativo de ensino de matemática")
@RequestMapping("gerencia/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Listar todas as categorias", description = "Recupera todas as categorias disponíveis no sistema.", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categorias encontradas"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaDTO> getAll() {
        return categoriaService.getAll();
    }

    @Operation(summary = "Buscar categoria por ID", description = "Recupera uma categoria específica pelo seu ID.", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoria encontrada"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria findById(@PathVariable UUID id_categoria) {
        return categoriaService.findById(id_categoria);
    }

    @Operation(summary = "Salvar nova categoria", description = "Cria uma nova categoria com os dados fornecidos.", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.save(categoriaDTO);
    }

    @Operation(summary = "Deletar categoria", description = "Remove uma categoria do sistema pelo seu ID.", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoriaDTO delete(@PathVariable UUID id_categoria) {
        return categoriaService.delete(id_categoria);
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria existente pelo seu ID.", responses = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PatchMapping("/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO update(@PathVariable UUID id_categoria, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.update(id_categoria, categoriaDTO);
    }

}
