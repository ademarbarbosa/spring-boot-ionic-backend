package br.com.coretecnologia.cursomc.services;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coretecnologia.cursomc.domain.ItemPedido;
import br.com.coretecnologia.cursomc.domain.PagamentoComBoleto;
import br.com.coretecnologia.cursomc.domain.Pedido;
import br.com.coretecnologia.cursomc.domain.enums.EstadoPagamento;
import br.com.coretecnologia.cursomc.repositories.ClienteRepository;
import br.com.coretecnologia.cursomc.repositories.ItemPedidoRepository;
import br.com.coretecnologia.cursomc.repositories.PagamentoRepository;
import br.com.coretecnologia.cursomc.repositories.PedidoRepository;
import br.com.coretecnologia.cursomc.repositories.ProdutoRepository;
import br.com.coretecnologia.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Pedido Pedido = pedidoRepository.findById(id).orElse(null);
		
		if (Pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		
		return Pedido;
	}

	public @Valid Pedido insert(@Valid Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findById(obj.getCliente().getId()).get());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findById(ip.getProduto().getId()).get());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
}
