package br.com.booksystem.livro.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.booksystem.model.LivroModel;
import br.com.booksystem.repository.LivroRepository;
//import org.junit.Test;..

@Named(value="consultarlivroController")
@ViewScoped
public class ConsultarLivroController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient
	private LivroModel livroModel;
 
	@Produces 
	private List<LivroModel> livros;
 
	@Inject transient
	private LivroRepository livroRepository;
 
	public List<LivroModel> getlivros() {
		return livros;
	}
	public void setlivros(List<LivroModel> livros) {
		this.livros = livros;
	}		
	public LivroModel getLivroModel() {
		return this.livroModel;
	}
	public void setLivroModel(LivroModel livroModel) {
		this.livroModel = livroModel;
	}
 
	/***
	 * CARREGA AS livroS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
 
		//RETORNAR AS livroS CADASTRADAS
		this.livros = livroRepository.GetLivros();
	}
 
	/***
	 * CARREGA INFORMAÇÕES DE UMA livro PARA SER EDITADA
	 * @param livroModel
	 */
	public void Editar(LivroModel livroModel){
		this.livroModel = livroModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 * @throws ParseException 
	 */
	public void AlterarRegistro() throws ParseException{
 
		this.livroRepository.AlterarRegistro(this.livroModel);	
 
 
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param livroModel
	 */
	public void ExcluirLivro(LivroModel livroModel){
 
		//EXCLUI A livro DO BANCO DE DADOS
		this.livroRepository.ExcluirRegistro(livroModel.getCodigo());
 
		//REMOVENDO O LIVRO DA LISTA
		//ASSIM QUE É O LIVRO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.livros.remove(livroModel);
 
	}
 
}
