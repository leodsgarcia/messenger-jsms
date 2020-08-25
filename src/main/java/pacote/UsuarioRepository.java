package pacote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	List<Usuario> getAllByNome(String nome);
	
	List<Usuario> findAll();
	
	List<Usuario> getAllByNomeAndSenha(String remetente, int senha);

	List<Usuario> findAllByNome(String nome);
	
	


}
