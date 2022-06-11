package lc.proyecto.certificacion.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lc.proyecto.certificacion.model.Curso;
import lc.proyecto.certificacion.model.Postulante;
import lc.proyecto.certificacion.repository.CursoRepository;
import lc.proyecto.certificacion.repository.PostulanteRepository;
import lc.proyecto.certificacion.security.Usuario;

@Controller
@RequestMapping("/postulante")
public class PostulanteController {
	
	@Autowired
	PostulanteRepository postulanteRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/panel")
	public String panelEstudiante(Postulante postulante, Principal principal, Model modelo) {
		Optional<Postulante> postulantePrincipal = postulanteRepository.findByRut(principal.getName());
		if(postulantePrincipal != null) {
			modelo.addAttribute(postulantePrincipal.get());
			return "postulante/panel";
		}
		return "postulante/panel";
	}

	
	@GetMapping("/postular/{id}")
	public String postular(@PathVariable("id") Curso curso, Authentication usuarioAutenticado
		, Model modelo
		) {		
			Usuario principal = (Usuario) usuarioAutenticado.getPrincipal();
			Postulante postulante = principal.getPostulante();
			postulante.setCurso(curso);
			postulanteRepository.save(postulante);
		
			return "redirect:/";
	}
	
	@GetMapping("/imagen/{id}")
	public ResponseEntity<byte[]> muestraImagenes(@PathVariable("id") Long id) throws SQLException, IOException {
		Optional<Postulante> postulante = postulanteRepository.findById(id);
		byte[] imageBytes = null;
		if(postulante.isPresent()) {
			imageBytes = postulante.get().getImagen();
			if(imageBytes == null) {
				imageBytes = Files.readAllBytes(Paths.get("src/main/resources/static/img/placeholder.png"));
			}
		}
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	@PostMapping("/subirimagen")
	public String procesarImagen(@RequestParam("image") MultipartFile imagen, Principal principal) throws IOException {
		byte[] contenidoImagen = imagen.getBytes();
		
		Optional<Postulante> postulantePrincipal = postulanteRepository.findByRut(principal.getName());
		postulantePrincipal.get().setImagen(contenidoImagen);
		postulanteRepository.saveAndFlush(postulantePrincipal.get());
		
		return "redirect:/postulante/inicio";
	}

}
