package com.COSTADev.usuario.controller;

import com.COSTADev.usuario.business.UsuarioService;
import com.COSTADev.usuario.business.ViaCepService;
import com.COSTADev.usuario.business.dto.EnderecoDTO;
import com.COSTADev.usuario.business.dto.TelefoneDTO;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.client.ViaCepDTO;
import com.COSTADev.usuario.infrasctruture.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro de Usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioControler {

    private final UsuarioService usuarioService;
    private final ViaCepService viaCepService;

    @PostMapping
    @Operation(summary = "Salvar Usuarios", description = "Criar um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    @Operation(summary = "Login de Usuarios", description = "Efetuar Login de Usuario")
    @ApiResponse(responseCode = "200", description = "Login feito com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias Inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<String> loginUsuario(@RequestBody UsuarioDTO usuarioDTO){
       return ResponseEntity.ok(usuarioService.autenticarUsuario(usuarioDTO));
    }

    @GetMapping
    @Operation(summary = "Buscar dados do Usuario por Email",
            description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "403", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuario", description = "Deleta usuario Por Email")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuario",
            description = "Atualizar dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,dto));
    }

    @PutMapping("/enderecos")
    @Operation(summary = "Atualizar Endereço do Usuario",
            description = "Atualizar endereço do usuario")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam ("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto));
    }


    @PutMapping("/telefones")
    @Operation(summary = "Atualizar Telefone do Usuario",
            description = "Atualizar telefone do usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam ("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto));
    }

    @PostMapping("/enderecos")
    @Operation(summary = "Salva Endereço do Usuario",
            description = "Salva endereço do usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTO> cadastroEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastroEndereco(token,dto));
    }

    @PostMapping("/telefones")
    @Operation(summary = "Salva Telefone do Usuario",
            description = "Salva telefone do usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTO> cadastroTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token,dto));
    }



    @GetMapping("/enderecos/{cep}")
    public ResponseEntity<ViaCepDTO>bsucarDadosCep(@PathVariable("cep")String cep){
        return ResponseEntity.ok(viaCepService.buscarDadosEndereco(cep));
    }
}
