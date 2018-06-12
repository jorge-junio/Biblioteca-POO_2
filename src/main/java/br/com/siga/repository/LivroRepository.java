package br.com.siga.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import br.com.siga.model.LivroModel;
import br.com.siga.model.UsuarioModel;
import br.com.siga.repository.entity.LivroEntity;
import br.com.siga.repository.entity.UsuarioEntity;
import br.com.siga.uteis.Uteis;
//import org.junit.Test;

public class LivroRepository {

	@Inject
	LivroEntity livroEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO LIVRO
	 * 
	 * @param livroModel
	 * @throws ParseException
	 */
	public void SalvarNovoRegistro(LivroModel livroModel) throws ParseException {

		entityManager = Uteis.JpaEntityManager();
		entityManager.getTransaction().begin();
		
		
		livroEntity = new LivroEntity();
		livroEntity.setCodigo(livroModel.getCodigo());
		livroEntity.setAno(livroModel.getAno());
		livroEntity.setGenero(livroModel.getGenero());
		livroEntity.setPrateleira(livroModel.getPrateleira());
		livroEntity.setAutor(livroModel.getAutor());
		livroEntity.setEmprestado(livroModel.getEmprestado());
		livroEntity.setIsbn(livroModel.getIsbn());
		livroEntity.setEditora(livroModel.getEditora());
		livroEntity.setEdicao(livroModel.getEdicao());
		livroEntity.setTitulo(livroModel.getTitulo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				livroModel.getUsuarioModel().getCodigo());
		livroEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(livroEntity);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public LivroEntity findLivro(Integer id) {
		return entityManager.find(LivroEntity.class, id);
	}

	/***
	 * MÉTODO PARA CONSULTAR O LIVRO
	 * 
	 * @return
	 */
	public List<LivroModel> GetLivros() {

		List<LivroModel> livrosModel = new ArrayList<LivroModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("LivroEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<LivroEntity> livrosEntity = (Collection<LivroEntity>) query.getResultList();

		LivroModel livroModel = null;

		for (LivroEntity livroEntity : livrosEntity) {

			livroModel = new LivroModel();
			livroModel.setCodigo(livroEntity.getCodigo());
			livroModel.setAno(livroEntity.getAno());
			livroModel.setGenero(livroEntity.getGenero());
			livroModel.setPrateleira(livroEntity.getPrateleira());
			livroModel.setAutor(livroEntity.getAutor());
			livroModel.setEmprestado(livroEntity.getEmprestado());
			livroModel.setIsbn(livroEntity.getIsbn());
			livroModel.setEditora(livroEntity.getEditora());
			livroModel.setEdicao(livroEntity.getEdicao());
			livroModel.setTitulo(livroEntity.getTitulo());
			livroModel.setDataCad(livroEntity.getDataCad());

			UsuarioEntity usuarioEntity = livroEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			livroModel.setUsuarioModel(usuarioModel);

			livrosModel.add(livroModel);
		}

		return livrosModel;

	}

	/***
	 * CONSULTA UM LIVRO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private LivroEntity GetLivro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(LivroEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param livroModel
	 * @throws ParseException
	 */
	public void AlterarRegistro(LivroModel livroModel) throws ParseException {
		EntityManager entityManager = null;
		try {
			entityManager = Uteis.JpaEntityManager();
			entityManager.getTransaction().begin();

			
			livroEntity = this.GetLivro(livroModel.getCodigo());
			livroEntity.setAno(livroModel.getAno());
			livroEntity.setGenero(livroModel.getGenero());
			livroEntity.setPrateleira(livroModel.getPrateleira());
			livroEntity.setAutor(livroModel.getAutor());
			livroEntity.setEmprestado(livroModel.getEmprestado());
			livroEntity.setIsbn(livroModel.getIsbn());
			livroEntity.setEditora(livroModel.getEditora());
			livroEntity.setEdicao(livroModel.getEdicao());
			livroEntity.setTitulo(livroModel.getTitulo());
			
			livroEntity = entityManager.merge(livroEntity);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = livroModel.getCodigo();
				if (findLivro(id) == null) {
					System.out.println("Livro não encontrado");
				}
			}
			throw ex;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {
		try {
			entityManager = Uteis.JpaEntityManager();
			entityManager.getTransaction().begin();
			LivroEntity livro = new LivroEntity();
			try {
				livro = entityManager.getReference(LivroEntity.class, codigo);
				livro.getCodigo();
			} catch (EntityNotFoundException enfe) {
				// throw new NonexistentEntityException("The avaliacao with id " + id + " no
				// longer exists.", enfe);
			}
			entityManager.remove(livro);
			entityManager.getTransaction().commit();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
