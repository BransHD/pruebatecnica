package com.pruebatecnica.apirest.Perfil;

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
@RequestMapping(path = "api/v1/Perfil")
public class PerfilController {
    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;

    }
    @GetMapping
    public List<Perfil> getUsuario(){
         return perfilService.getPerfil();
    }
	@PostMapping
    public ResponseEntity<Object> RegistrarPerfil(@RequestBody Perfil perfil){
        return this.perfilService.newPerfil(perfil);
    }
    @PutMapping
    public ResponseEntity<Object> ActualizarPerfil(@RequestBody Perfil perfil){
        return this.perfilService.newPerfil(perfil);
    }
    //Eliminar
    @DeleteMapping(path = "{usuarioId}")
    public ResponseEntity<Object> EliminarPerfil(@PathVariable("usuarioId")Integer id){
        return this.perfilService.deletePerfil(id);
    }
}
