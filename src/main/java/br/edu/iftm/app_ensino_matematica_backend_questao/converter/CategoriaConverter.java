package br.edu.iftm.app_ensino_matematica_backend_questao.converter;

import com.example.dtos.CategoriaDTO;

import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;

public class CategoriaConverter {
    
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