package br.com.siga.model;


import java.time.LocalDateTime;


public class LivroModel {
	
	private Integer         codigo;
	private Integer         ano;
	private String          genero;
	private String          prateleira;
	private String          autor;
	private String          emprestado;
	private String          isbn;
	private String          editora;
	private Integer         edicao;
	private String          titulo;
	private LocalDateTime	dataCad;
	private UsuarioModel   usuarioModel;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getPrateleira() {
		return prateleira;
	}
	public void setPrateleira(String prateleira) {
		this.prateleira = prateleira;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEmprestado() {
		return emprestado;
	}
	public void setEmprestado(String emprestado) {
		this.emprestado = emprestado;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public Integer getEdicao() {
		return edicao;
	}
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDateTime getDataCad() {
		return dataCad;
	}
	public void setDataCad(LocalDateTime dataCad) {
		this.dataCad = dataCad;
	}
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	
}
