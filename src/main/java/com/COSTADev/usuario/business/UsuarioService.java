package com.COSTADev.usuario.business;

import com.COSTADev.usuario.business.converter.UsuarioConverter;
import com.COSTADev.usuario.business.dto.EnderecoDTO;
import com.COSTADev.usuario.business.dto.TelefoneDTO;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.entity.Endereco;
import com.COSTADev.usuario.infrasctruture.entity.Telefone;
import com.COSTADev.usuario.infrasctruture.entity.Usuario;
import com.COSTADev.usuario.infrasctruture.exceptions.ConflictException;
import com.COSTADev.usuario.infrasctruture.exceptions.ResourceNotFoundException;
import com.COSTADev.usuario.infrasctruture.repository.EnderecosRepository;
import com.COSTADev.usuario.infrasctruture.repository.TelefoneRepository;
import com.COSTADev.usuario.infrasctruture.repository.UsuarioRepository;
import com.COSTADev.usuario.infrasctruture.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecosRepository enderecosRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvarUsuario (UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificarEmailExistente(email);
            if (existe){
                throw new ConflictException("Email já cadastrado." + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscaUsuarioPorEmail(String email){
        try{
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.findByEmail(email)
                        .orElseThrow(()
                -> new ResourceNotFoundException("Usuario não encontrado. " + email))
        );
    }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Usuario não encontrado. " + email);
        }
    }


    public void deletarUsuarioPorEmail(String email){
         usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token,  UsuarioDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setSenha(dto.getSenha()!= null ? passwordEncoder.encode(dto.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado"));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }


    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO){
        Endereco entity = enderecosRepository.findById(idEndereco).orElseThrow(()->
                new ResourceNotFoundException("Id não localizado" + idEndereco));

        Endereco endereco =  usuarioConverter.updateEndereco(enderecoDTO, entity );

        return usuarioConverter.paraEnderecoDTO(enderecosRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(()->
                new ResourceNotFoundException("Id não localizado" + idTelefone));

        Telefone telefone =  usuarioConverter.updateTelefone(dto, entity);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

    public EnderecoDTO cadastroEndereco(String token, EnderecoDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()
                -> new ResourceNotFoundException("Email nao localizado " + email ));

        Endereco endereco = usuarioConverter.paraEnderecoEntity(dto, usuario.getId());
        Endereco enderecoEntity = enderecosRepository.save(endereco);
        return usuarioConverter.paraEnderecoDTO(enderecoEntity);
    }

    public TelefoneDTO cadastroTelefone(String token, TelefoneDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()
                -> new ResourceNotFoundException("Email nao localizado " + email ));

        Telefone telefone = usuarioConverter.paraTelefoneEntity(dto, usuario.getId());
        Telefone telefoneEntity = telefoneRepository.save(telefone);
        return usuarioConverter.paraTelefoneDTO(telefoneEntity);
    }

}
