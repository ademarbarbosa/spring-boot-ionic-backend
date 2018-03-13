package br.com.coretecnologia.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.coretecnologia.cursomc.domain.Pedido;
import br.com.coretecnologia.cursomc.repositories.PedidoRepository;
import br.com.coretecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido Pedido = pedidoRepository.findById(id).orElse(null);
		
		if (Pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		
		return Pedido;
	}
	
}
