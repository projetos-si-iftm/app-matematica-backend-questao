package br.edu.iftm.app_ensino_matematica_backend_questao.controller;

import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImagemController {

    private final GridFsTemplate gridFsTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        try {
            GridFsResource resource = gridFsTemplate.getResource(gridFsTemplate.findOne(
                org.springframework.data.mongodb.core.query.Query.query(
                    org.springframework.data.mongodb.core.query.Criteria.where("_id").is(new ObjectId(id))
                )
            ));
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(resource.getContentType()));
            headers.setContentDisposition(
                org.springframework.http.ContentDisposition.inline()
                    .filename(resource.getFilename())
                    .build()
            );
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}