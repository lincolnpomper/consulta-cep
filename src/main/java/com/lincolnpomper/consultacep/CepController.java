package com.lincolnpomper.consultacep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public CepResponse consultarCep(@PathVariable @NotNull String cep) {
        return cepService.consultarCep(cep);
    }

    @PostMapping("/consultarCep/")
    public CepResponse consultarCepPost(@Valid @RequestBody CepRequest cep) {
        return cepService.consultarCep(cep.getCep());
    }
}
