package com.COSTADev.usuario.business;

import com.COSTADev.usuario.business.converter.UsuarioConverter;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.entity.Usuario;
import com.COSTADev.usuario.infrasctruture.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvarUsuario (UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }


}
