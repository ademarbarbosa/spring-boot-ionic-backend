package br.com.coretecnologia.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coretecnologia.cursomc.domain.Cliente;
import br.com.coretecnologia.cursomc.repositories.ClienteRepository;
import br.com.coretecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		
		return cliente;
	}
	
}
