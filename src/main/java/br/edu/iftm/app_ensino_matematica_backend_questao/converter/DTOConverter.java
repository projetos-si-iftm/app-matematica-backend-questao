package br.edu.iftm.app_ensino_matematica_backend_questao.converter;

import java.util.UUID;
import java.util.stream.Collectors;

import com.example.dtos.AlternativaDTO;
import com.example.dtos.QuestaoDTO;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Alternativa;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Questao;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.CategoriaService;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.UrlBuilderService;

public class DTOConverter {
    
    public static QuestaoDTO convert(Questao questao, UrlBuilderService urlBuilderService) {
        QuestaoDTO questaoDTO = new QuestaoDTO();
        questaoDTO.setId_questao(questao.getId_questao());
        questaoDTO.setTitulo(questao.getTitulo());
        questaoDTO.setEnunciado(questao.getEnunciado());
        questaoDTO.setImagem(urlBuilderService.buildImageUrl(questao.getImagem()));
        questaoDTO.setDificuldade(questao.getDificuldade());
        
        // Converter Alternativa para AlternativaDTO
        questaoDTO.setAlternativa(questao.getAlternativa().stream()
            .map(alternativa -> new AlternativaDTO(alternativa.getResposta(), alternativa.isCorreta()))
            .collect(Collectors.toList()));
        
        // Usar o CategoriaConverter para converter as categorias
        questaoDTO.setCategoria(questao.getCategoria().stream()
            .map(CategoriaConverter::convert)
            .collect(Collectors.toList()));
        
        return questaoDTO;
    }

    public static Questao convertToEntity(QuestaoDTO questaoDTO, CategoriaService categoriaService) {
        Questao questao = new Questao();
        questao.setId_questao(questaoDTO.getId_questao() != null ? questaoDTO.getId_questao() : UUID.randomUUID());
        questao.setTitulo(questaoDTO.getTitulo());
        questao.setEnunciado(questaoDTO.getEnunciado());
        questao.setImagem(questaoDTO.getImagem());
        questao.setDificuldade(questaoDTO.getDificuldade());
        
        // Converter AlternativaDTO para Alternativa
        questao.setAlternativa(questaoDTO.getAlternativa().stream()
            .map(alternativaDTO -> new Alternativa(alternativaDTO.getResposta(), alternativaDTO.isCorreta()))
            .collect(Collectors.toList()));
        
        // Buscar as categorias completas pelo ID (mantém a lógica original)
        questao.setCategoria(questaoDTO.getCategoria().stream()
            .map(categoriaDTO -> {
                Categoria categoria = categoriaService.findById(categoriaDTO.getId_categoria());
                return categoria;
            })
            .collect(Collectors.toList()));
        
        return questao;
    }
}