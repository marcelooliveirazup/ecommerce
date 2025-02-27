package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(unique = true)
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @Email(message = "O email deve ser válido")
    @Column(unique = true)
    private String email;
}

