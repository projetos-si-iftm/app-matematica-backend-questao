package br.edu.iftm.app_ensino_matematica_backend_questao.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class UrlBuilderService {
    @Value("${app.base-url}")
    private String baseUrl;

    public String buildImageUrl(String imageId) {
        return String.format("%s/api/images/%s", baseUrl, imageId);
    }
}