package com.pruebatecnica.apirest.Usuario;

import com.pruebatecnica.apirest.Perfil.Perfil;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codiusua")
    private Integer codiusua;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "logiusua", nullable = false, length = 50)
    private String logiusua;

    @Column(name = "correo", length = 150)
    private String correo;

    @Column(name = "celular", nullable = false, length = 9)
    private String celular;

    @Column(name = "fotopath", length = 255)
    private String fotopath;

    @ManyToOne
    @JoinColumn(name = "codiperfil", nullable = false)
    private Perfil perfil;

    @Column(name = "password", length = 80)
    private String password;

    public Usuario(){     
    }
    public Usuario(Integer codiusua, String dni, String logiusua, String correo, String celular, String fotopath,
            Perfil perfil, String password) {
        this.codiusua = codiusua;
        this.dni = dni;
        this.logiusua = logiusua;
        this.correo = correo;
        this.celular = celular;
        this.fotopath = fotopath;
        this.perfil = perfil;
        this.password = password;
    }
}
