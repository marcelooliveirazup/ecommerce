package com.ecommerce.controller;

import com.ecommerce.model.Compra;
import com.ecommerce.service.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody Map<String, Object> payload) {
        String cpf = (String) payload.get("cpf");
        List<String> nomesProdutos = (List<String>) payload.get("produtos");

        Compra compra = compraService.realizarCompra(cpf, nomesProdutos);
        return ResponseEntity.ok(compra);
    }
}
