package com.COSTADev.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private Long numero;
    private String rua;
    private int cep;
    private String cidade;
    private String estado;
    private String complemento;
}
