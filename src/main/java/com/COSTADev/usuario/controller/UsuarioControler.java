package com.COSTADev.usuario.controller;

import com.COSTADev.usuario.business.UsuarioService;
import com.COSTADev.usuario.business.dto.UsuarioDTO;
import com.COSTADev.usuario.infrasctruture.entity.Usuario;
import com.COSTADev.usuario.infrasctruture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControler {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }


   @PostMapping("/login")
    public String loginUsuario(@RequestBody UsuarioDTO usuarioDTO){
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
               usuarioDTO.getEmail(), usuarioDTO.getSenha()));
    return "Bearer " + jwtUtil.generateToken(authentication.getName());
   }

    @GetMapping
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email){
         usuarioService.deletarUsuarioPorEmail(email);
         return ResponseEntity.ok().build();
    }

}
