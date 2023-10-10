package com.lincolnpomper.consultacep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CepControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testConsultarCep() {

        String cep = "80730-250";
        String url = "http://localhost:" + port + "/cep/" + cep;

        ResponseEntity<CepResponse> responseEntity = restTemplate.getForEntity(url, CepResponse.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();

        CepResponse cepResponse = responseEntity.getBody();
        assertThat(cepResponse).isNotNull();
        assertThat(cepResponse.getCep()).isEqualTo(cep);
        assertThat(cepResponse.getLogradouro()).isEqualTo("Rua Francisco Lachowski");
    }

    @Test
    public void testConsultarCepVazio() {

        String cep = "";
        String url = "http://localhost:" + port + "/cep/" + cep;

        ResponseEntity<CepResponse> responseEntity = restTemplate.getForEntity(url, CepResponse.class);
        assertThat(responseEntity.getStatusCode().is4xxClientError()).isTrue();
    }

    @Disabled
    @Test
    public void testConsultarCepBodyValido() throws Exception {

        CepRequest cepRequest = new CepRequest("80730-250");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CepRequest> requestEntity = new HttpEntity<>(cepRequest, headers);
        String url = "http://localhost:" + port + "/cep/consultarCep/";

        ResponseEntity<CepResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, CepResponse.class);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();

        ResponseEntity<CepResponse> cepResponse = restTemplate.getForEntity(url, CepResponse.class);
        assertThat(cepResponse).isNotNull();
        assertThat(cepResponse.getBody().getCep()).isEqualTo(cepRequest.getCep());
        assertThat(cepResponse.getBody().getLogradouro()).isEqualTo("Rua Francisco Lachowski");
    }
}
