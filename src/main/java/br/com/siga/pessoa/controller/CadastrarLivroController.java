package br.com.siga.pessoa.controller;

import java.text.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.siga.model.LivroModel;
import br.com.siga.repository.LivroRepository;
import br.com.siga.usuario.controller.UsuarioController;
import br.com.siga.uteis.Uteis;

//import org.junit.Test;

@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarLivroController {
 
	@Inject
	LivroModel pessoaModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	LivroRepository pessoaRepository;
 
 
	public LivroModel getPessoaModel() {
		return pessoaModel;
	}
 
	public void setPessoaModel(LivroModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 * @throws ParseException 
	 */
	public void SalvarNovaPessoa() throws ParseException{
 
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
 
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
 
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
 
		this.pessoaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}
