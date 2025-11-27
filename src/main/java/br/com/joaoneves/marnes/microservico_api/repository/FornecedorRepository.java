package br.com.joaoneves.marnes.microservico_api.repository;

import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
