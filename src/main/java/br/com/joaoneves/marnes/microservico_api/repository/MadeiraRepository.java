package br.com.joaoneves.marnes.microservico_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import java.util.List;

@Repository
public interface MadeiraRepository extends JpaRepository<Madeira, Long> {
    List<Madeira> findByCategoriaId(Long categoriaId);
}