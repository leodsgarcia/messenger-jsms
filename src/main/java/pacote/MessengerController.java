package pacote;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessengerController {

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private MensagemRepository mr;
	
	@PostMapping("/menu")
	@ResponseBody
	public String irMenu() {
		return "<script> location.href=\"menu.html\";</script>";
	}
	
	@PostMapping("/cadastro1")
	@ResponseBody
	public String irCadastro1() {
		return "<script> location.href=\"cadastro1.html\";</script>";
	}


	@PostMapping("/cadastro")
	@ResponseBody
	public String cadastrar(@RequestParam (required = true) String nome, @RequestParam (required = true) int senha) {
		Usuario usuario = new Usuario();

		List<Usuario> nomesCadastrados = new ArrayList<>();

		nomesCadastrados = ur.getAllByNome(nome);

		if (nomesCadastrados.size() == 0) {
			usuario.setNome(nome);
			usuario.setSenha(senha);

			ur.save(usuario);
		return "<script> location.href=\"menu.html\"; alert(\"Usuário cadastrado com sucesso!\") </script>";
		} else {
			return "<script> location.href=\"cadastro2.html\"; alert(\"Nome de usuário já cadastrado!\") </script>";
		}

	}
	
	@PostMapping("/cadastro2")
	@ResponseBody
	public String cadastrar1(@RequestParam (required = true) String nome, @RequestParam (required = true) int senha) {
		Usuario usuario = new Usuario();

		List<Usuario> nomesCadastrados = new ArrayList<>();

		nomesCadastrados = ur.getAllByNome(nome);

		if (nomesCadastrados.size() == 0) {
			  

			usuario.setNome(nome);
			usuario.setSenha(senha);

			ur.save(usuario);
		return "<script> location.href=\"menu.html\"; alert(\"Usuário cadastrado com sucesso!\") </script>";
		} else {
			return "<script> location.href=\"cadastro1.html\"; alert(\"Nome de usuário já cadastrado!\") </script>";
		}

	}
	

	
	@PostMapping("/mensagem")
	@ResponseBody
	public String listarMensagens(@RequestParam(required = true) String nome,
			@RequestParam(required = true) int senha,
			@RequestParam String buscar) {

		List<Usuario> usuarioValido = new ArrayList<>();

		usuarioValido = ur.getAllByNomeAndSenha(nome, senha);
		
		

		if (usuarioValido.size() == 0) {
			return "<script> location.href=\"mensagem.html\"; alert(\"Usuário inválido.\") </script>";
		} else {

			List<Mensagem> mensagensRecebidas = new ArrayList<>();
			
			if(buscar == "" ) {

			mensagensRecebidas = mr.findAllByDestinatario(nome, 
					new Sort(Sort.Direction.ASC,"dataEnvio"));
						if (mensagensRecebidas.size() == 0) {
							return "<script> location.href=\"mensagem.html\"; alert(\"Você não tem nenhuma mensagem.\") </script>";

			} else {
				StringBuilder mostrarMensagens = new StringBuilder();
				mostrarMensagens.append("<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"layout.css\">\r\n" + 
						" <link rel=\"shortcut icon\" href=\"imagens/sistema/icon3.png\">\r\n" + 
						"<meta charset=\"UTF-8\">\r\n" + 
						"<title>Messenger JSMS | Mensagens</title>\r\n" + 
						"</head>\r\n" + 
						"<body class=\"body2\">\r\n" + 
						"	\r\n" + 
						"	<header>\r\n" + 
						"		      <nav>\r\n" + 
						"		        <ul>\r\n" + 
						"             <li><img id=\"logoNav\"src=\"imagens/sistema/logo7.png\" alt=\"\"></li>\r\n" + 
						"    	      <li><a id=\"fonte1\" href=\"menu.html\">Menu Principal</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"enviar.html\">Enviar Mensagem</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"cadastro2.html\">Cadastrar Usuário</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"usuarios.html\">Visualizar Usuários</a></li>\r\n" + 
						"					  </ul>\r\n" + 
						"		      </nav>\r\n" + 
						"				</header>\r\n" + 
						"				\r\n" + 
						"				<br>\r\n" + 
						"				<br>\r\n" + 
						"				<br>\r\n" + 
						"		\r\n" + 
						"	\r\n" + 
						"\r\n" + 
						"		\r\n" + 
						"</body>\r\n" + 
						"</html>");
				mostrarMensagens.append("<div id='divCadastro' align='center'>");
				
				for (Mensagem m : mensagensRecebidas) {					
					System.out.println(m.getTexto());
					mostrarMensagens.append("<p>");
					mostrarMensagens.append(" | " + m.getRemetente().getNome() + " | "+ m.getDataEnvio() + " <br>"
							+ "------------------------<br> " + m.getTexto()).append("<br><br />");
					mostrarMensagens.append("<p>");
					
				}
				 
				return mostrarMensagens.toString();
				 
			}
		}else {
			mensagensRecebidas = mr. 
					findAllByDestinatarioIgnoreCaseAndTextoContainingIgnoreCase
					(nome, buscar,  
							new Sort(Sort.Direction.ASC,"dataEnvio"));
 
			if (mensagensRecebidas.size() == 0) {
				return "<script> location.href=\"mensagem.html\"; alert(\"Você não tem nenhuma mensagem contendo esta pesquisa.\") </script>";

			} else {
				StringBuilder mostrarMensagens = new StringBuilder();
				mostrarMensagens.append("<html>\r\n" + 
						"<head>\r\n" + 
						"<link rel=\"stylesheet\" type=\"text/css\" href=\"layout.css\">\r\n" + 
						" <link rel=\"shortcut icon\" href=\"imagens/sistema/icon3.png\">\r\n" + 
						"<meta charset=\"UTF-8\">\r\n" + 
						"<title>Messenger JSMS | Mensagens</title>\r\n" + 
						"</head>\r\n" + 
						"<body class=\"body2\">\r\n" + 
						"	\r\n" + 
						"	<header>\r\n" + 
						"		      <nav>\r\n" + 
						"		        <ul>\r\n" + 
						"             <li><img id=\"logoNav\"src=\"imagens/sistema/logo7.png\" alt=\"\"></li>\r\n" + 
						"    	      <li><a id=\"fonte1\" href=\"menu.html\">Menu Principal</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"enviar.html\">Enviar Mensagem</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"cadastro2.html\">Cadastrar Usuário</a></li>\r\n" + 
						"            <li><a id=\"fonte1\" href=\"usuarios.html\">Visualizar Usuários</a></li>\r\n" + 
						"					  </ul>\r\n" + 
						"		      </nav>\r\n" + 
						"				</header>\r\n" + 
						"				\r\n" + 
						"				<br>\r\n" + 
						"				<br>\r\n" + 
						"				<br>\r\n" + 
						"		\r\n" + 
						"	\r\n" + 
						"\r\n" + 
						"		\r\n" + 
						"</body>\r\n" + 
						"</html>");
			
				mostrarMensagens.append("<div id='divCadastro' align='center'>");
				for (Mensagem m : mensagensRecebidas) {
				
					System.out.println(m.getTexto());
					mostrarMensagens.append("<p>");
					mostrarMensagens.append(" | " + m.getRemetente().getNome() + " | "+ m.getDataEnvio() + " <br>"
							+ "------------------------<br> " + m.getTexto()).append("<br><br />");
					mostrarMensagens.append("<p>");
					
				}
				
				return mostrarMensagens.toString();
				
			}
		}
		}
	
	}


	@PostMapping("/enviar")
	@ResponseBody
	public String enviarMensagem(@RequestParam(required = true) String remetente,
			@RequestParam(required = true) int senha, @RequestParam(required = true) String destinatario,
			@RequestParam(required = true) String texto) {

		Mensagem mensagem = new Mensagem();

		List<Usuario> remetenteCadastrado = new ArrayList<>();
		List<Usuario> destinatarioCadastrado = new ArrayList<>();

		remetenteCadastrado = ur.getAllByNomeAndSenha(remetente, senha);
		destinatarioCadastrado = ur.getAllByNome(destinatario);

		if (remetenteCadastrado.size() == 0) {
			return "<script> location.href=\"enviar.html\"; alert(\"Usuário inválido\") </script>";

		} else {
			if (destinatarioCadastrado.size() == 0) {
				return "<script> location.href=\"enviar.html\"; alert(\"Nenhum destinatário encontrado.\") </script>";


			} else {
				Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
				mensagem.setDestinatario(destinatario);
				mensagem.setRemetente(remetenteCadastrado.get(0));
				mensagem.setTexto(texto);
				mensagem.setDataEnvio(dataAtual);
				mr.save(mensagem);
				return "<script> location.href=\"menu.html\"; alert(\"Mensagem enviada com sucesso.\") </script>";

		}

		}

	}
	@PostMapping("/usuarios")
	@ResponseBody

	public String listarUsuarios(@RequestParam String nome) {
	List<Usuario> listarUsuarios = new ArrayList<>();
	


	if(nome == "") {
	listarUsuarios = ur.findAll();

	if (listarUsuarios.size() == 0) {
		return "<script> location.href=\"menu.html\"; alert(\"Nenhum usuário cadastrado.\") </script>";

	} else {
		StringBuilder mostrarUsuarios = new StringBuilder();
		
		
		
		
		
		mostrarUsuarios.append("<html>\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"layout.css\">\r\n" + 
				"	<link rel=\"shortcut icon\" href=\"imagens/sistema/icon3.png\">\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<title> Messenger JSMS | Cadastro </title>\r\n" + 
				"	<body class=\"body2\">\r\n" + 
				"\r\n" + 
				"    	<header>\r\n" + 
				"		      <nav>\r\n" + 
				"		        <ul>\r\n" + 
				"             <li><img id=\"logoNav\"src=\"imagens/sistema/logo7.png\" alt=\"\"></li>\r\n" + 
				"            <li><a id=\"fonte1\" href=\"menu.html\">Menu Principal</a></li>\r\n" + 
				"            <li><a id=\"fonte1\" href=\"cadastro2.html\">Cadastrar Usuarios</a></li>\r\n" + 
				"            <li><a id=\"fonte1\" href=\"enviar.html\">Enviar Mensagem</a></li>\r\n" + 
				"            <li><a id=\"fonte1\" href=\"mensagem.html\">Visualizar Mensagens</a></li>\r\n" + 
				"					  </ul>\r\n" + 
				"		      </nav>\r\n" + 
				"				</header>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		</form>\r\n" + 

				"	</body>\r\n" + 
				"</head>\r\n" + 
				"</html>\r\n");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<br>");
		mostrarUsuarios.append("<div id='divCadastro' align='center'>");
		
		
		
		
		
		for (Usuario u : listarUsuarios) {	
			
			mostrarUsuarios.append("<p>");
			mostrarUsuarios.append(u.getNome());
			mostrarUsuarios.append("<p>");
		
			
			
		}
		
		return mostrarUsuarios.toString();
		
		
	}
	}else {
		listarUsuarios = ur.findAllByNome(nome);

		if (listarUsuarios.size() == 0) {
			return "<script> location.href=\"usuarios.html\"; alert(\"Usuário não encontrado.\") </script>";

		} else {
			StringBuilder mostrarUsuarios = new StringBuilder();
		
			mostrarUsuarios.append("<html>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"layout.css\">\r\n" + 
					"	<link rel=\"shortcut icon\" href=\"imagens/sistema/icon3.png\">\r\n" + 
					"	<meta charset=\"UTF-8\">\r\n" + 
					"	<title> Messenger JSMS | Cadastro </title>\r\n" + 
					"	<body class=\"body2\">\r\n" + 
					"\r\n" + 
					"    	<header>\r\n" + 
					"		      <nav>\r\n" + 
					"		        <ul>\r\n" + 
					"             <li><img id=\"logoNav\"src=\"imagens/sistema/logo7.png\" alt=\"\"></li>\r\n" + 
					"            <li><a id=\"fonte1\" href=\"menu.html\">Menu Principal</a></li>\r\n" + 
					"            <li><a id=\"fonte1\" href=\"usuarios.html\">Visualizar Usuários</a></li>\r\n" + 
					"            <li><a id=\"fonte1\" href=\"enviar.html\">Enviar Mensagem</a></li>\r\n" + 
					"            <li><a id=\"fonte1\" href=\"mensagem.html\">Visualizar Mensagens</a></li>\r\n" + 
					"					  </ul>\r\n" + 
					"		      </nav>\r\n" + 
					"				</header>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"		</form>\r\n" + 
					"	</body>\r\n" + 
					"</head>\r\n" + 
					"</html>\r\n");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<br>");
			mostrarUsuarios.append("<div id='divCadastro' align='center'>");
			
			for (Usuario u : listarUsuarios) {					
				mostrarUsuarios.append("<p>");
				mostrarUsuarios.append(u.getNome());
				mostrarUsuarios.append("<p>");
				
			}
			
			return mostrarUsuarios.toString();
			
			
		
		}
	}
		}
}
	

