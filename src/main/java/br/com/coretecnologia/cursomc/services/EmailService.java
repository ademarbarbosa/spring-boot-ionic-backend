package br.com.coretecnologia.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import br.com.coretecnologia.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
