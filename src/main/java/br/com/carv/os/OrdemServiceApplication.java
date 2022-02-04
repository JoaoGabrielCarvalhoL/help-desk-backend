package br.com.carv.os;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.carv.os.repositories.ClienteRepository;
import br.com.carv.os.services.TecnicoService;

@SpringBootApplication
public class OrdemServiceApplication  implements CommandLineRunner {

	@Autowired
	TecnicoService service;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(OrdemServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		Tecnico tecnico = new Tecnico(null, "Joao Gabriel Carvalho Teste", "491.899.400-83", "teste@gmail.com", "123456");
		TecnicoDTO dto = new TecnicoDTO(tecnico);
		service.save(dto);
		*/
		

	}

}
