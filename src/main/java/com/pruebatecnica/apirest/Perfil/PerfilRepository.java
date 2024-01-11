package com.pruebatecnica.apirest.Perfil;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
    Optional<Perfil> findBynombperfil(String nombperfil);
}
