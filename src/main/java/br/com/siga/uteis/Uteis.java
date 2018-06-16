package br.com.siga.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class Uteis {
 
	private static EntityManagerFactory EMF;
	private static EntityManager conexao;

	public static EntityManager getConexao() {
		conexao = JpaEntityManager().createEntityManager();
		return conexao;
	}

	public static EntityManagerFactory JpaEntityManager(){
		if (EMF == null){
			EMF = Persistence.createEntityManagerFactory("unit_app");
		}
		return EMF;
	}
	
	
 
	//MOSTRAR MENSAGEM
	public static void Mensagem(String mensagem){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}
 
	//MOSTRAR MENSAGEM
	public static void MensagemAtencao(String mensagem){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencao:", mensagem));
	}
 
	//MOSTRAR MENSAGEM
	public static void MensagemInfo(String mensagem){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}
 
}