package com.ecommerce.service;

import com.ecommerce.model.Cliente;
import com.ecommerce.model.Compra;
import com.ecommerce.model.Produto;
import com.ecommerce.repository.ClienteRepository;
import com.ecommerce.repository.CompraRepository;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;

    public CompraService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, CompraRepository compraRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
    }

    public Compra realizarCompra(String cpf, List<String> nomesProdutos) {
        // Buscar o cliente pelo CPF
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        // Buscar os produtos pelo nome
        List<Produto> produtos = nomesProdutos.stream()
                .map(nome -> produtoRepository.findByNome(nome)
                        .orElseThrow(() -> new RuntimeException("Produto " + nome + " não encontrado.")))
                .toList();

        // Verificar estoque dos produtos
        for (Produto produto : produtos) {
            if (produto.getQuantidade() <= 0) {
                throw new RuntimeException("Produto " + produto.getNome() + " está em falta.");
            }
            produto.setQuantidade(produto.getQuantidade() - 1);
            produtoRepository.save(produto);
        }

        // Criar a compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setProdutos(produtos);
        compra.setDataCompra(LocalDateTime.now());

        return compraRepository.save(compra);
    }
}
