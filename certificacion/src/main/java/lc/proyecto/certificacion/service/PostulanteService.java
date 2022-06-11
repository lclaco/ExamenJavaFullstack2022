package lc.proyecto.certificacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lc.proyecto.certificacion.model.Postulante;
import lc.proyecto.certificacion.repository.PostulanteRepository;

@Service
public class PostulanteService {

	@Autowired
	private PostulanteRepository postulanteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Postulante crearPostulante(Postulante postulante) {
		String contrasenaCodificada = passwordEncoder.encode(postulante.getContrasena());
		postulante.setContrasena(contrasenaCodificada);
		return postulanteRepository.save(postulante);
	}
	
	public long contarPostulantes() {
		return postulanteRepository.count();
	}
}
