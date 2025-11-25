package com.COSTADev.usuario.business.converter;

import com.COSTADev.usuario.business.dto.EnderecosDTO;
import com.COSTADev.usuario.business.dto.TelefoneDTO;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.entity.Enderecos;
import com.COSTADev.usuario.infrasctruture.entity.Telefone;
import com.COSTADev.usuario.infrasctruture.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecosDTOS()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefoneDTOS()))
               .build();
    }


    public List<Enderecos> paraListaEndereco (List<EnderecosDTO> enderecosDTOS){
            return enderecosDTOS.stream().map(this::paraEnderecos).toList();
    }

    public Enderecos paraEnderecos(EnderecosDTO enderecosDTO){
        return Enderecos.builder()
                .rua(enderecosDTO.getRua())
                .cidade(enderecosDTO.getCidade())
                .estado(enderecosDTO.getEstado())
                .numero(enderecosDTO.getNumero())
                .complemento(enderecosDTO.getComplemento())
                .cep(enderecosDTO.getCep())
                .build();
    }


    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


    /// separacao pra service ///

    public UsuarioDTO paraUsuarioDTO (Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecosDTOS()))
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefoneDTOS()))
                .build();
}


public List<EnderecosDTO> paraListaEnderecoDTO (List<Enderecos> enderecosDTOS){
    return enderecosDTOS.stream().map(this::paraEnderecos).toList();
}

public EnderecosDTO paraEnderecos (Enderecos enderecosDTO){
    return EnderecosDTO.builder()
            .rua(enderecosDTO.getRua())
            .cidade(enderecosDTO.getCidade())
            .estado(enderecosDTO.getEstado())
            .numero(enderecosDTO.getNumero())
            .complemento(enderecosDTO.getComplemento())
            .cep(enderecosDTO.getCep())
            .build();
}


public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefoneDTOS){
    return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
}

public TelefoneDTO paraTelefoneDTO (Telefone telefoneDTO){
    return TelefoneDTO.builder()
            .numero(telefoneDTO.getNumero())
            .ddd(telefoneDTO.getDdd())
            .build();
}
}
