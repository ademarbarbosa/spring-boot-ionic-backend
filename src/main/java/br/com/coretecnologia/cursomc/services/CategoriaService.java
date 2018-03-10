package br.com.coretecnologia.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.coretecnologia.cursomc.domain.Categoria;
import br.com.coretecnologia.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Categoria categoria =categoriaRepository.findById(id).orElse(null);
		return categoria;
	}
	
}
