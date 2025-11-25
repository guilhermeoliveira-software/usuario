package com.COSTADev.usuario.infrasctruture.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
@Builder
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "numero")
    private Long numero;
    @Column(name = "rua")
    private String rua;
    @Column(name = "cep", length = 8)
    private int cep;
    @Column(name = "cidade", length = 150)
    private String cidade;
    @Column(name = "estado",length = 2)
    private String estado;
    @Column(name = "complemento", length = 20)
    private String complemento;

}
