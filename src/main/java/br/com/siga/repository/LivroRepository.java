package br.com.siga.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import br.com.siga.model.LivroModel;
import br.com.siga.model.UsuarioModel;
import br.com.siga.repository.entity.PessoaEntity;
import br.com.siga.repository.entity.UsuarioEntity;
import br.com.siga.uteis.Uteis;
//import org.junit.Test;

public class LivroRepository {

	@Inject
	PessoaEntity pessoaEntity;

	EntityManager entityManager;

	/***
	 * M�TODO RESPONS�VEL POR SALVAR UMA NOVA PESSOA
	 * 
	 * @param pessoaModel
	 * @throws ParseException
	 */
	public void SalvarNovoRegistro(LivroModel pessoaModel) throws ParseException {

		entityManager = Uteis.JpaEntityManager();
		entityManager.getTransaction().begin();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse(pessoaModel.getData());

		pessoaEntity = new PessoaEntity();
		pessoaEntity.setCodigo(pessoaModel.getCodigo());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());
		pessoaEntity.setData(dataFormatada);
		pessoaEntity.setIdade(pessoaModel.getIdade());
		pessoaEntity.setPai(pessoaModel.getPai());
		pessoaEntity.setMae(pessoaModel.getMae());
		pessoaEntity.setTelefone(pessoaModel.getTelefone());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setDataCad(LocalDateTime.now());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());
		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public PessoaEntity findPessoa(Integer id) {
		return entityManager.find(PessoaEntity.class, id);
	}

	/***
	 * M�TODO PARA CONSULTAR A PESSOA
	 * 
	 * @return
	 */
	public List<LivroModel> GetPessoas() {

		List<LivroModel> pessoasModel = new ArrayList<LivroModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		LivroModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {

			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formatador.format(pessoaEntity.getData());

			pessoaModel = new LivroModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setNome(pessoaEntity.getNome());
			pessoaModel.setData(dataFormatada);
			pessoaModel.setIdade(pessoaEntity.getIdade());
			pessoaModel.setPai(pessoaEntity.getPai());
			pessoaModel.setMae(pessoaEntity.getMae());
			pessoaModel.setTelefone(pessoaEntity.getTelefone());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setDataCad(pessoaEntity.getDataCad());

			if (pessoaEntity.getSexo().equals("M"))
				pessoaModel.setSexo("Masculino");
			else
				pessoaModel.setSexo("Feminino");

			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);

			pessoasModel.add(pessoaModel);
		}

		return pessoasModel;

	}

	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO C�DIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private PessoaEntity GetPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param pessoaModel
	 * @throws ParseException
	 */
	public void AlterarRegistro(LivroModel pessoaModel) throws ParseException {
		EntityManager entityManager = null;
		try {
			entityManager = Uteis.JpaEntityManager();
			entityManager.getTransaction().begin();

			SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada2 = formato2.parse(pessoaModel.getData());

			pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());
			pessoaEntity.setNome(pessoaModel.getNome());
			pessoaEntity.setSexo(pessoaModel.getSexo());
			pessoaEntity.setData(dataFormatada2);
			pessoaEntity.setIdade(pessoaModel.getIdade());
			pessoaEntity.setEndereco(pessoaModel.getEndereco());
			pessoaEntity.setPai(pessoaModel.getPai());
			pessoaEntity.setMae(pessoaModel.getMae());
			pessoaEntity.setTelefone(pessoaModel.getTelefone());

			pessoaEntity = entityManager.merge(pessoaEntity);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = pessoaModel.getCodigo();
				if (findPessoa(id) == null) {
					System.out.println("Pessoa n�o encontrada");
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
			PessoaEntity pessoa = new PessoaEntity();
			try {
				pessoa = entityManager.getReference(PessoaEntity.class, codigo);
				pessoa.getCodigo();
			} catch (EntityNotFoundException enfe) {
				// throw new NonexistentEntityException("The avaliacao with id " + id + " no
				// longer exists.", enfe);
			}
			entityManager.remove(pessoa);
			entityManager.getTransaction().commit();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
