package br.edu.iftm.app_ensino_matematica_backend_questao.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO.QuestaoDTO;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.QuestaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("manage/question")
public class QuestaoController {

    private final QuestaoService questaoService;
    private final GridFsTemplate gridFsTemplate;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/{id_questao}")
    @ResponseStatus(HttpStatus.OK)
    public QuestaoDTO findById(@PathVariable UUID id_questao) {
        return questaoService.findById(id_questao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> getAll() {
        return questaoService.getAll();
    }

    @GetMapping("/category/{id_categoria}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findByCategoria(@PathVariable UUID id_categoria) {
        return questaoService.findByCategoria(id_categoria);
    }

    @GetMapping("/category/dificulty")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findByCategoriaAndDificuldade(id_categoria, dificuldade);
    }

    @GetMapping("/category/dificulty/search")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestaoDTO> findRandomByCategoriaAndDificuldade(@RequestParam UUID id_categoria, @RequestParam int dificuldade) {
        return questaoService.findRandomByCategoriaAndDificuldade(id_categoria, dificuldade);
    }
}
