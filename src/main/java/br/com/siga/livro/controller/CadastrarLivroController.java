package br.com.siga.livro.controller;

import java.text.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.siga.model.LivroModel;
import br.com.siga.repository.LivroRepository;
import br.com.siga.usuario.controller.UsuarioController;
import br.com.siga.uteis.Uteis;

//import org.junit.Test;

@Named(value="cadastrarlivroController")
@RequestScoped
public class CadastrarLivroController {
 
	@Inject
	LivroModel livroModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	LivroRepository livroRepository;
 
 
	public LivroModel getlivroModel() {
		return livroModel;
	}
 
	public void setlivroModel(LivroModel livroModel) {
		this.livroModel = livroModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 * @throws ParseException 
	 */
	public void SalvarNovoLivro() throws ParseException{
 
		livroModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
 
		livroRepository.SalvarNovoRegistro(this.livroModel);
 
		this.livroModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}
