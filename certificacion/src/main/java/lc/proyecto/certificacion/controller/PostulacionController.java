package lc.proyecto.certificacion.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lc.proyecto.certificacion.model.Curso;
import lc.proyecto.certificacion.model.Postulacion;
import lc.proyecto.certificacion.model.Postulante;
import lc.proyecto.certificacion.repository.CursoRepository;
import lc.proyecto.certificacion.repository.PostulacionRepository;
import lc.proyecto.certificacion.security.Usuario;

@Controller
@RequestMapping("/postulacion")
public class PostulacionController {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private PostulacionRepository postulacionRepository;
	
	
	@GetMapping("/registrar/{id}")
	public String registrarPostulacion(@PathVariable("id") Curso curso, Authentication autenticacion, Model modelo) {
		
		Usuario usuario = (Usuario) autenticacion.getPrincipal();
		if(usuario.getPostulante() != null) {	
			Postulante postulante = usuario.getPostulante();
			List<Postulacion> postulaciones = postulacionRepository.findByPostulante(usuario.getPostulante());
			
			if(postulaciones.size() > 0) {
				modelo.addAttribute("error", "Ya estas inscrito en un curso, solo puedes postular a 1");
				return "postulante/panel";
			}
				Postulacion postulacion = Postulacion.builder()
						.postulante(postulante)
						.curso(curso)
						.fecha(LocalDateTime.now())
						.build();
				//Disminuir cupos del curso
				int cuposDisponibles = curso.getPostulacionesDisponibles() - 1;
				curso.setPostulacionesDisponibles(cuposDisponibles);
				
				cursoRepository.save(curso);
				postulacionRepository.save(postulacion);
				return "redirect:/postulante/panel";
		}else {
			return"error/noencontrada";
		}
	}
}
