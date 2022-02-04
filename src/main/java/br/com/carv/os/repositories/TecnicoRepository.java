package br.com.carv.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.os.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
