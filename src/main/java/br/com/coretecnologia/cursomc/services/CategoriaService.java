package br.com.coretecnologia.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.coretecnologia.cursomc.domain.Categoria;
import br.com.coretecnologia.cursomc.repositories.CategoriaRepository;
import br.com.coretecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Categoria categoria =categoriaRepository.findById(id).orElse(null);
		
		if (categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		
		return categoria;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return categoriaRepository.save(obj);
	}
	
}
