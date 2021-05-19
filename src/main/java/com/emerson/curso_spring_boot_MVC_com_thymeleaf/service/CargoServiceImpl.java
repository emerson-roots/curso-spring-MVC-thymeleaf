package com.emerson.curso_spring_boot_MVC_com_thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.dao.CargoDao;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.domain.Cargo;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.util.PaginacaoUtil;

//aula 25
//o atributo readOnly possui como padrao o valor FALSE e nao serie necessário colocar neste caso
//porém na aula, foi inserido apenas para conhecimento
//a obrigatoriedade serie necessária caso quisessemos setar como TRUE
@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDao dao;

	@Override
	public void salvar(Cargo cargo) {
		dao.save(cargo);

	}

	@Override
	public void editar(Cargo cargo) {
		dao.update(cargo);

	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		return dao.findAll();
	}
	
	//aula 47
	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if (buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

	//aula 82
	@Override
	public PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcaoOrdenacao) {
		return dao.buscaPaginada(pagina, direcaoOrdenacao);
	}

}
