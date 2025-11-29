package com.COSTADev.usuario.business.dto;

import com.COSTADev.usuario.infrasctruture.entity.Enderecos;
import com.COSTADev.usuario.infrasctruture.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;

    private List<EnderecosDTO> enderecosDTOS;
    private List<TelefoneDTO> telefoneDTOS;
}
