package br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO;

import java.util.UUID;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private UUID id_categoria;
    private String nome;

    public static CategoriaDTO convert(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId_categoria(categoria.getId_categoria());
        categoriaDTO.setNome(categoria.getNome());
        return categoriaDTO;
    }

    public static Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId_categoria(categoriaDTO.getId_categoria());
        categoria.setNome(categoriaDTO.getNome());
        return categoria;
    }
}