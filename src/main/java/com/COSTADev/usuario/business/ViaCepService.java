package com.COSTADev.usuario.business;

import com.COSTADev.usuario.infrasctruture.client.ViaCepClient;
import com.COSTADev.usuario.infrasctruture.client.ViaCepDTO;
import com.COSTADev.usuario.infrasctruture.exceptions.IllegalArgumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ViaCepService {
    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep) {
        return client.buscaDadosEndereco(cep);
    }

    public String processarCep(String cep) {
        String cepFormatado = cep.replace("-", "").
                replace(" ", "");

        if (!cepFormatado.matches("\\d+") || !Objects.equals (cepFormatado.length(), 8)) {

            throw new IllegalArgumentException("O cep contém caracteres invalidos, favor verificar.");

        }
        return cepFormatado;
    }

}
