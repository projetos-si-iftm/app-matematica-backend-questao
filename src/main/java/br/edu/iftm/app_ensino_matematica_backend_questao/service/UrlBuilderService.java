package br.edu.iftm.app_ensino_matematica_backend_questao.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlBuilderService {
    @Value("${app.base-url}")
    private String baseUrl;

    public String buildImageUrl(String imageId) {
        return String.format("%s/api/imagens/%s", baseUrl, imageId);
    }
}