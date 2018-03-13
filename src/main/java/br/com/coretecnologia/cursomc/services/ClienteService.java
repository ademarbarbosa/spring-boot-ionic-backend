package br.com.coretecnologia.cursomc.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.coretecnologia.cursomc.domain.Cliente;
import br.com.coretecnologia.cursomc.dto.ClienteDTO;
import br.com.coretecnologia.cursomc.repositories.ClienteRepository;
import br.com.coretecnologia.cursomc.services.exceptions.DataIntegrityException;
import br.com.coretecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		
		return cliente;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	public void delete(Integer id) {
		Cliente obj = find(id);
		try {
			clienteRepository.delete(obj);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
