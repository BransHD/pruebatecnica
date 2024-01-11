package com.pruebatecnica.apirest.Perfil;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class PerfilService {
    HashMap<String, Object> datos = new HashMap<>();

    private final PerfilRepository perfilRepository;
    
    public PerfilService(PerfilRepository perfilRepository){
        this.perfilRepository = perfilRepository;
    }
    public List<Perfil> getPerfil(){
		return this.perfilRepository.findAll();
	}

    //Graba y actualiza un Perfil
    @Transactional
    public ResponseEntity<Object> newPerfil(Perfil perfil) {
        Optional<Perfil> respuesta = perfilRepository.findBynombperfil(perfil.getNombperfil());
        datos = new HashMap<>();


        if(respuesta.isPresent() && perfil.getCodiperfil()==null){
            datos.put("Error",true);
            datos.put("Mensaje:","Ya existe un perfil con ese nombre");
            return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
            );
        }
        // Guardar el perfil en la base de datos        
        datos.put("Mensaje:","Se guardo exitosamente!");
        //Actualiza el perfil
        if(perfil.getCodiperfil()!=null){
            datos.put("Mensaje:","Se actualizo el Perfil con Exito");
        }
        perfilRepository.save(perfil);
        datos.put("data",perfil);  
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
            );    
    }

    public ResponseEntity<Object> deletePerfil(Integer codiperfil) {
        datos = new HashMap<>();
        boolean existe = this.perfilRepository.existsById(codiperfil);
        if (!existe) {
            datos.put("error", true);
            datos.put("Mensaje:", "No existe un Perfil con ese ID");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        try {
            perfilRepository.deleteById(codiperfil);
            datos.put("Mensaje:", "Perfil Eliminado Exitosamente");
            return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
        } catch (DataIntegrityViolationException e) {
            // Manejar la excepción de violación de integridad referencial
            datos.put("error", true);
            datos.put("Mensaje:", "No se puede eliminar el perfil porque está siendo utilizado por otras tablas.");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }
    }
}
