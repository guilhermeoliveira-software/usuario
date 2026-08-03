package com.COSTADev.usuario.business;

import com.COSTADev.usuario.infrasctruture.client.ViaCepClient;
import com.COSTADev.usuario.infrasctruture.client.ViaCepDTO;
import com.COSTADev.usuario.infrasctruture.exceptions.IllegalArgumentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ViaCepService {
    private final ViaCepClient client;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public ViaCepDTO buscarDadosEndereco(String cep) {
        String cepLimpo = processarCep(cep);
        String key = "endereços" + cepLimpo;
        ViaCepDTO dto = client.buscaDadosEndereco(cep);

        if (Objects.nonNull(dto)) {
            String stringDTO = objectMapper.writeValueAsString(dto);

            redisTemplate.opsForValue().set(key, stringDTO, Duration.ofDays(30));
        }
            return dto;
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
