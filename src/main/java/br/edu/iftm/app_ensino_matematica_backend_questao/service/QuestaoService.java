package br.edu.iftm.app_ensino_matematica_backend_questao.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dtos.QuestaoDTO;

import br.edu.iftm.app_ensino_matematica_backend_questao.converter.DTOConverter;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Questao;
import br.edu.iftm.app_ensino_matematica_backend_questao.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final UrlBuilderService urlBuilderService;
    private final QuestaoRepository questaoRepository;
    private final CategoriaService categoriaService;

    public QuestaoDTO save(QuestaoDTO questaoDTO) {
        Questao questao = DTOConverter.convertToEntity(questaoDTO, categoriaService);
        questao.setId_questao(UUID.randomUUID()); // Gerar UUID para a questão
        questao = questaoRepository.save(questao);
        return DTOConverter.convert(questao, urlBuilderService);
    }

    public QuestaoDTO findById(UUID id_questao) {
        Questao questao = questaoRepository.findById(id_questao)
            .orElseThrow(() -> new RuntimeException("Questão não encontrada"));
        return DTOConverter.convert(questao, urlBuilderService);
    }

    public List<QuestaoDTO> getAll() {
        List<Questao> questoes = questaoRepository.findAll();
        return questoes.stream()
            .map(q -> DTOConverter.convert(q, urlBuilderService))
            .collect(Collectors.toList());
    }

    public List<QuestaoDTO> findByCategoria(UUID id_categoria) {
        List<Questao> questoes = questaoRepository.findByCategoriaId(id_categoria);
        return questoes.stream()
            .map(q -> DTOConverter.convert(q, urlBuilderService))
            .collect(Collectors.toList());
    }

    public List<QuestaoDTO> findByCategoriaAndDificuldade(UUID id_categoria, int dificuldade) {
        List<Questao> questoes = questaoRepository.findByCategoriaIdAndDificuldade(id_categoria, dificuldade);
        return questoes.stream()
            .map(q -> DTOConverter.convert(q, urlBuilderService))
            .collect(Collectors.toList());
    }

    public List<QuestaoDTO> findRandomByCategoriaAndDificuldade(UUID id_categoria, int dificuldade) {
        List<Questao> questoes = questaoRepository.findRandomByCategoriaIdAndDificuldade(id_categoria, dificuldade);
        return questoes.stream()
            .map(q -> DTOConverter.convert(q, urlBuilderService))
            .collect(Collectors.toList());
    }
}