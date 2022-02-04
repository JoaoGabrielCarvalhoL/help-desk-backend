package br.com.carv.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.os.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
