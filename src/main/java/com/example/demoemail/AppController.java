package com.example.demoemail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	
	@GetMapping("/send_html_email")
	public String sendHTMLEmail(Model model) throws MessagingException {
		String from = "fa.qanbari.i@gmail.com";
		String to = "ho3nbadri@gmail.com";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setSubject("This is an HTML email");
		helper.setFrom(from);
		helper.setTo(to);

		boolean html = true;
		helper.setText("<b>Hey ostad</b>,<br><i>finally I do it</i>", html);

		mailSender.send(message);
		
		model.addAttribute("message", "An HTML email has been sent");
		return "result";		
	}
	

}
