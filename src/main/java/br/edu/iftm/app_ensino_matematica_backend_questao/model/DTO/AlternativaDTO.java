// package br.edu.iftm.app_ensino_matematica_backend_questao.model.DTO;

// import java.util.UUID;

// import br.edu.iftm.app_ensino_matematica_backend_questao.model.Alternativa;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class AlternativaDTO {
//     private UUID id_alternativa;
//     private String resposta;
//     private boolean correta;

//     public static AlternativaDTO convert(Alternativa alternativa) {
//         AlternativaDTO alternativaDTO = new AlternativaDTO();
//         alternativaDTO.setId_alternativa(alternativa.getId_alternativa());
//         alternativaDTO.setResposta(alternativa.getResposta());
//         alternativaDTO.setCorreta(alternativa.isCorreta());
//         return alternativaDTO;
//     }

//     public static Alternativa convertToEntity(AlternativaDTO alternativaDTO) {
//         Alternativa alternativa = new Alternativa();
//         alternativa.setId_alternativa(alternativaDTO.getId_alternativa() != null ? alternativaDTO.getId_alternativa() : UUID.randomUUID());
//         alternativa.setResposta(alternativaDTO.getResposta());
//         alternativa.setCorreta(alternativaDTO.isCorreta());
//         return alternativa;
//     }
// }