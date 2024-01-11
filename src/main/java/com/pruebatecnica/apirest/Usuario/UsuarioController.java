package com.pruebatecnica.apirest.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/Usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;

    }
    @GetMapping
    public List<Usuario> getUsuarios(){
         return usuarioService.getUsuarios();
    }
	@PostMapping
    public ResponseEntity<Object> RegistrarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.newUsuario(usuario);
    }
    @PutMapping
    public ResponseEntity<Object> ActualizarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.newUsuario(usuario);
    }
    //Eliminar
    @DeleteMapping(path = "{usuarioId}")
    public ResponseEntity<Object> EliminarUsuario(@PathVariable("usuarioId")Integer id){
        return this.usuarioService.deleteUsuario(id);
    }

}
