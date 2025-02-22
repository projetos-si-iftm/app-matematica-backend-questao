package br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Alternativa;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Questao;
import br.edu.iftm.app_ensino_matematica_backend_questao.service.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestaoDTO {
    private UUID id_questao;
    private String titulo;
    private String enunciado;
    private String imagem;
    private int dificuldade;
    private List<Alternativa> alternativa;
    private List<CategoriaDTO> categoria;

    public static QuestaoDTO convert(Questao questao) {
        QuestaoDTO questaoDTO = new QuestaoDTO();
        questaoDTO.setId_questao(questao.getId_questao());
        questaoDTO.setTitulo(questao.getTitulo());
        questaoDTO.setEnunciado(questao.getEnunciado());
        questaoDTO.setImagem(questao.getImagem());
        questaoDTO.setDificuldade(questao.getDificuldade());
        questaoDTO.setAlternativa(questao.getAlternativa());
        questaoDTO.setCategoria(questao.getCategoria().stream().map(CategoriaDTO::convert).collect(Collectors.toList()));
        return questaoDTO;
    }

    public static Questao convertToEntity(QuestaoDTO questaoDTO, CategoriaService categoriaService) {
        Questao questao = new Questao();
        questao.setId_questao(questaoDTO.getId_questao() != null ? questaoDTO.getId_questao() : UUID.randomUUID());
        questao.setTitulo(questaoDTO.getTitulo());
        questao.setEnunciado(questaoDTO.getEnunciado());
        questao.setImagem(questaoDTO.getImagem());
        questao.setDificuldade(questaoDTO.getDificuldade());
        questao.setAlternativa(questaoDTO.getAlternativa());
        questao.setCategoria(questaoDTO.getCategoria().stream().map(categoriaDTO -> {
            Categoria categoria = categoriaService.findById(categoriaDTO.getId_categoria());
            return categoria;
        }).collect(Collectors.toList()));
        return questao;
    }
}