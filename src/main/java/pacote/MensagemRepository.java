package pacote;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository  extends JpaRepository <Mensagem, Integer>{

	List<Mensagem> findAll();

	List<Mensagem> 
	findAllByDestinatario
	(String nome, Sort s);
	
	List<Mensagem> findAllByDestinatarioIgnoreCaseAndTextoContainingIgnoreCase(String nome, String buscar, Sort sort);




	
	
}

