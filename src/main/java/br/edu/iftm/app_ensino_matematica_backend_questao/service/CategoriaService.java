package br.edu.iftm.app_ensino_matematica_backend_questao.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dtos.CategoriaDTO;

import br.edu.iftm.app_ensino_matematica_backend_questao.converter.CategoriaConverter;
import br.edu.iftm.app_ensino_matematica_backend_questao.model.Categoria;
import br.edu.iftm.app_ensino_matematica_backend_questao.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
            .map(CategoriaConverter::convert)
            .collect(Collectors.toList());
    }

    public Categoria findById(UUID id_categoria) {
        return categoriaRepository.findById(id_categoria)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId_categoria(UUID.randomUUID());
        categoria.setNome(categoriaDTO.getNome());
        categoria = categoriaRepository.save(categoria);
        return CategoriaConverter.convert(categoria);
    }

    public CategoriaDTO delete(UUID id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaRepository.delete(categoria);
        return CategoriaConverter.convert(categoria);
    }

    public CategoriaDTO update(UUID id_categoria, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id_categoria)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        if (categoriaDTO.getNome() != null && !categoria.getNome().equals(categoriaDTO.getNome())) {
            categoria.setNome(categoriaDTO.getNome());
        }
        categoria = categoriaRepository.save(categoria);
        return CategoriaConverter.convert(categoria);
    }
}