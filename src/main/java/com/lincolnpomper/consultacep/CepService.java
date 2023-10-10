package com.lincolnpomper.consultacep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private final RestTemplate restTemplate;
    private final String cepApiUrl;

    @Autowired
    public CepService(RestTemplate restTemplate, @Value("${app.cep-api-url}") String cepApiUrl) {
        this.restTemplate = restTemplate;
        this.cepApiUrl = cepApiUrl;
    }

    public CepResponse consultarCep(String cep) {
        String url = cepApiUrl + cep + "/json/";
        return restTemplate.getForObject(url, CepResponse.class);
    }
}
