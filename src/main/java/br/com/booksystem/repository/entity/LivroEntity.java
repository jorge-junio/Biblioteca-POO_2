package br.com.booksystem.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
 
@Entity
@Table(name="livro")
@NamedQueries({
	 
	@NamedQuery(name = "LivroEntity.findAll",query= "SELECT p FROM LivroEntity p")
 
})
public class LivroEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer codigo;
 
	@Column(name = "ano")
	private Integer ano;
 
	@Column(name = "genero")
	private String  genero;
 
	@Column(name = "prateleira")
	private String prateleira;
 
	@Column(name = "autor")
	private String autor;
 
	@Column(name = "emprestado")
	private String  emprestado;
 
	@Column(name = "isbn")
	private String  isbn;
	
	@Column(name = "editora")
	private String  editora;
	
	@Column(name = "edicao")
	private Integer edicao;
	
	@Column(name = "titulo")
	private String  titulo;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime	dataCad;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

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

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
 
 
}