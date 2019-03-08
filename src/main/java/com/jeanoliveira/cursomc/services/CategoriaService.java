package com.jeanoliveira.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanoliveira.cursomc.domain.Categoria;
import com.jeanoliveira.cursomc.repositories.CategoriaRepository;
import com.jeanoliveira.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		//posso udar o metodo buscar acima para verificar primeiro se ele existe antes de atualizar
		// o propio metodo esta preparado para lançar uma execçao caso nao o encontre
		find(obj.getId());
		return repo.save(obj);
	}
}
