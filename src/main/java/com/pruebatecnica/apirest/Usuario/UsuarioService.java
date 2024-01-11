package com.pruebatecnica.apirest.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    HashMap<String, Object> datos = new HashMap<>();

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    //Graba y actualiza un Usuario
    @Transactional
    public ResponseEntity<Object> newUsuario(Usuario usuario) {
        Optional<Usuario> respuesta = usuarioRepository.findByLogiusua(usuario.getLogiusua());
        datos = new HashMap<>();
        

        if(respuesta.isPresent() && usuario.getCodiusua()==null){
            datos.put("Error",true);
            datos.put("Mensaje:","Ya existe un usuario con ese nombre");
            return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
            );
        }
        if (usuario.getPerfil() == null || usuario.getPerfil().getCodiperfil() == 0) {
            datos.put("Error", true);
            datos.put("Mensaje", "El perfil del usuario no puede ser nulo o tener un código de perfil igual a 0");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }

        

        // Guardar el usuario en la base de datos        
        datos.put("Mensaje:","Se guardo exitosamente!");
        //Actualiza el usuario
        if(usuario.getCodiusua()!=null){
            datos.put("Mensaje:","Se actualizo el Usuario con Exito");
        }
        usuarioRepository.save(usuario);
        datos.put("data",usuario);  
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
            );
    }
    
    public ResponseEntity<Object> deleteUsuario(Integer codiusua){
        datos = new HashMap<>();
        boolean existe = this.usuarioRepository.existsById(codiusua);
        if(!existe){
            datos.put("error",true);
            datos.put("Mensaje:","No existe un usuario con ese ID");
            return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
            );
        }
        usuarioRepository.deleteById(codiusua);
        datos.put("Mensaje:","Usuario Eliminado Existosamente");
        return new ResponseEntity<>(
            datos,
            HttpStatus.ACCEPTED
        );
    }

}
