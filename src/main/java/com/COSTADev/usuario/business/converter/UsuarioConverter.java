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
                .telefones(paraListaTelefoneDTO(usuarioDTO.getTelefones()))
                .build();
}


public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecos){
    return enderecos.stream().map(this::paraEnderecoDTO).toList();
}

public EnderecoDTO paraEnderecoDTO (Endereco endereco){
    return EnderecoDTO.builder()
            .id(endereco.getId())
            .rua(endereco.getRua())
            .cidade(endereco.getCidade())
            .estado(endereco.getEstado())
            .numero(endereco.getNumero())
            .complemento(endereco.getComplemento())
            .cep(endereco.getCep())
            .build();
}


public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefone){
    return telefone.stream().map(this::paraTelefoneDTO).toList();
}

public TelefoneDTO paraTelefoneDTO (Telefone telefone){
    return TelefoneDTO.builder()
            .id(telefone.getId())
            .numero(telefone.getNumero())
            .ddd(telefone.getDdd())
            .build();
}


public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .Id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
}

    public Endereco updateEndereco(EnderecoDTO dto, Endereco entity) {
        return Endereco.builder()
                .Id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .build();
    }


    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity) {
        return Telefone.builder()
                .Id(entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .build();
    }


    public Endereco paraEnderecoEntity(EnderecoDTO dto, Long idUsuario){
        return Endereco.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .usuario_id(idUsuario)
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO dto, Long idUsuario){
        return Telefone.builder()
                .numero(dto.getNumero())
                .ddd(dto.getDdd())
                .usuario_id(idUsuario)
                .build();
    }
}
