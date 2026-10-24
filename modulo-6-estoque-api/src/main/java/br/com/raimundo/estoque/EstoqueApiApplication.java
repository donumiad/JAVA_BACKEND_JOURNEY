package br.com.raimundo.estoque;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EstoqueApiApplication {

	private static final Logger log =
			LoggerFactory.getLogger(
					EstoqueApiApplication.class
			);
	public static void main(String[] args) {
		SpringApplication.run
				(EstoqueApiApplication.class,
						args);
		log.info("Estoque API iniciada com sucesso");
	}

}
