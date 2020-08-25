package pacote;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mensagem")
public class Mensagem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mensagem")
	private Integer idMensagem;
	
	@Column(name="texto")
	private String texto;
	
	@Column(name="destinatario")
	private String destinatario;
	
	@Column(name="data_envio")
	private Timestamp dataEnvio;

	@ManyToOne
	private Usuario remetente; 
	
	public Mensagem() {
		
	}

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Timestamp getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Timestamp dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public Mensagem(Integer idMensagem, String texto, String destinatario, Timestamp dataEnvio, Usuario remetente) {
		super();
		this.idMensagem = idMensagem;
		this.texto = texto;
		this.destinatario = destinatario;
		this.dataEnvio = dataEnvio;
		this.remetente = remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}


}
