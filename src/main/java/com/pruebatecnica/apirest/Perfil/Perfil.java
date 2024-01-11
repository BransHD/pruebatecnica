package com.pruebatecnica.apirest.Perfil;

import java.util.List;

import com.pruebatecnica.apirest.Usuario.Usuario;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Perfil")
@Data
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codiperfil")
    private Integer codiperfil;
     @Column(name = "nombperfil", nullable = false)
    private String nombperfil;

    public Perfil(){
    }

    public Perfil(int codiperfil, String nombperfil) {
        this.codiperfil = codiperfil;
        this.nombperfil = nombperfil;
    }

}
