package lc.proyecto.certificacion.repository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguridadController {

	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
}
