package com.COSTADev.usuario.business.converter;

import com.COSTADev.usuario.business.dto.EnderecoDTO;
import com.COSTADev.usuario.business.dto.TelefoneDTO;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.entity.Endereco;
import com.COSTADev.usuario.infrasctruture.entity.Telefone;
import com.COSTADev.usuario.infrasctruture.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
               .build();
    }


    public List<Endereco> paraListaEndereco (List<EnderecoDTO> enderecoDTOS){
            //return enderecosDTOS.stream().map(this::paraEnderecos).toList();
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecoDTOS){
            enderecos.add(paraEnderecos(enderecoDTO));
        }
        return enderecos;
    }

    public Endereco paraEnderecos(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .build();
    }


    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefones){
        // return telefones.stream().map(this::paraTelefone).toList();
        if (telefones == null || telefones.isEmpty()) {
            return List.of(); // retorna lista vazia, nunca null
        }

        return telefones.stream()
                .map(dto -> Telefone.builder()
                        .numero(dto.getNumero())
                        .ddd(dto.getDdd())
                        .build()
                )
                .toList();
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
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefone(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
}


public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecos){
    return enderecos.stream().map(this::paraEnderecos).toList();
}

public EnderecoDTO paraEnderecos (Endereco enderecoDTO){
    return EnderecoDTO.builder()
            .rua(enderecoDTO.getRua())
            .cidade(enderecoDTO.getCidade())
            .estado(enderecoDTO.getEstado())
            .numero(enderecoDTO.getNumero())
            .complemento(enderecoDTO.getComplemento())
            .cep(enderecoDTO.getCep())
            .build();
}


public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefone){
    return telefone.stream().map(this::paraTelefoneDTO).toList();
}

public TelefoneDTO paraTelefoneDTO (Telefone telefoneDTO){
    return TelefoneDTO.builder()
            .numero(telefoneDTO.getNumero())
            .ddd(telefoneDTO.getDdd())
            .build();
}
}
