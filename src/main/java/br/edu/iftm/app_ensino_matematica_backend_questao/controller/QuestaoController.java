package br.edu.iftm.app_ensino_matematica_backend_questao.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dtos.QuestaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.iftm.app_ensino_matematica_backend_questao.service.QuestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Questão", description = "Gerenciamento de questões do aplicativo de ensino de matemática")
@RequestMapping("gerencia/questao")
public class QuestaoController {

    private final QuestaoService questaoService;
    private final GridFsTemplate gridFsTemplate;

    @Operation(summary = "Salvar questão", description = "Cria ou atualiza uma questão com os dados fornecidos.", responses = {
        @ApiResponse(responseCode = "201", description = "Questão salva com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public QuestaoDTO save(
            @RequestPart("dados") String dadosJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) throws IOException {
        // 1. Converte o JSON recebido como string para QuestaoDTO
        ObjectMapper mapper = new ObjectMapper();
        QuestaoDTO questaoDTO = mapper.readValue(dadosJson, QuestaoDTO.class);

        // 2. Se imagem for enviada, salva no MongoDB e armazena o ID no DTO
        if (imagem != null && !imagem.isEmpty()) {
            ObjectId imagemId = gridFsTemplate.store(imagem.getInputStream(), imagem.getOriginalFilename(), imagem.getContentType());
            questaoDTO.setImagem(imagemId.toString()); // salva o ID da imagem
        }

        // 3. Salva a questão e retorna o DTO com URL da imagem montada
        return questaoService.save(questaoDTO);
    }

    @Operation(summary = "Buscar questão por ID", description = "Busca uma questão pelo seu ID.", responses = {
        @ApiResponse(responseCode = "200", description = "Questão encontrada"),
        @ApiResponse(responseCode = "404", description = "Questão não encontrada")
    })
    @GetMapping("/{id_questao}")
    @ResponseStatus(HttpStatus.OK)
    public QuestaoDTO findById(@PathVariable UUID id_questao) {
        return questaoService.findById(id_questao);
    }

    @Operation(summary = "Buscar todas as questões", description = "Busca todas as questões cadastradas no sistema.", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de questões retornada"),
        @ApiResponse(responseCode = "404", description = "Nenhuma questão encontrada")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> getAll() {
        return questaoService.getAll();
    }

    @Operation(
            summary = "Buscar questões por categoria",
            description = "Busca todas as questões associadas a uma categoria específica.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de questões por categoria retornada"),
                @ApiResponse(responseCode = "404", description = "Nenhuma questão encontrada para a categoria")
            }
    )
    @GetMapping("/categoria/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findByCategoria(@PathVariable UUID id_categoria) {
        return questaoService.findByCategoria(id_categoria);
    }

    @Operation(summary = "Buscar questões por categoria e dificuldade", description = "Busca todas as questões associadas a uma categoria específica e com um nível de dificuldade determinado.", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de questões por categoria e dificuldade retornada"),
        @ApiResponse(responseCode = "404", description = "Nenhuma questão encontrada para a categoria e dificuldade")
    })
    @GetMapping("/categoria/dificuldade")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findByCategoriaAndDificuldade(id_categoria, dificuldade);
    }

    @Operation(summary = "Buscar questões aleatórias por categoria e dificuldade", description = "Busca uma questão aleatória associada a uma categoria específica e com um nível de dificuldade determinado.", responses = {
        @ApiResponse(responseCode = "200", description = "Questão aleatória por categoria e dificuldade retornada"),
        @ApiResponse(responseCode = "404", description = "Nenhuma questão encontrada para a categoria e dificuldade")
    })
    @GetMapping("/categoria/dificuldade/busca")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findRandomByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findRandomByCategoriaAndDificuldade(id_categoria, dificuldade);
    }

    @Operation(summary = "Deletar questão", description = "Remove uma questão do sistema pelo seu ID.", responses = {
        @ApiResponse(responseCode = "204", description = "Questão deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Questão não encontrada")
    })
    @DeleteMapping("/{id_questao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id_questao) {
        questaoService.delete(id_questao);

    }

}
