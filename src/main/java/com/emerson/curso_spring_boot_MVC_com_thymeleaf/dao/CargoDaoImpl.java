package com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.util.PaginacaoUtil;

//aula 22
@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	// aula 81
	public PaginacaoUtil<Cargo> buscaPaginada(int pagina) {

		// define a quantidade de registros por paginas
		int tamanhoRegistrosPorPagina = 5;

		// decrementa o numero da pagina recebida devido o metodo setFirsResult()
		// trabalhar com index de posição inicial 0
		// e logo depois multiplica pela qtd de registros por pagina
		// para definir o index do 1° registro de cada pagina
		int inicio = (pagina - 1) * tamanhoRegistrosPorPagina;

		List<Cargo> cargos = getEntityManager()
				.createQuery("select c from Cargo c order by c.nome asc", Cargo.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanhoRegistrosPorPagina)
				.getResultList();
		
		// aula 82 - continuação da construção do metodo
		long qtdRegistrosNaTabela = count();
		long totalDePaginas = (qtdRegistrosNaTabela + (tamanhoRegistrosPorPagina - 1)) / tamanhoRegistrosPorPagina;
		
		return new PaginacaoUtil<>(tamanhoRegistrosPorPagina, pagina, totalDePaginas, cargos);
	}
	
	// aula 82
	// retorna a quantidade de registros na tabela
	public long count() {
		return getEntityManager().createNamedQuery("select count(*) from Cargo", Long.class)
				.getSingleResult();
	}
}
