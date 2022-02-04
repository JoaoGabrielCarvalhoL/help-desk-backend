package br.com.carv.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.os.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
