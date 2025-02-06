package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Column(unique = true)
    private String nome;

    @Min(value = 1, message = "O preço deve ser maior que zero")
    private Double preco;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private int quantidade;
}
