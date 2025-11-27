package br.com.joaoneves.marnes.microservico_api.repository;

import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MadeiraRepository extends JpaRepository<Madeira, Long> {
    List<Madeira> findByTipoContainingIgnoreCase(String tipo);
}
