package com.COSTADev.usuario.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecosDTO {

    private Long numero;
    private String rua;
    private int cep;
    private String cidade;
    private String estado;
    private String complemento;
}
