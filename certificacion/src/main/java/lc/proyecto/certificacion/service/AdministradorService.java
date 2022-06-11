package lc.proyecto.certificacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lc.proyecto.certificacion.model.Administrador;
import lc.proyecto.certificacion.repository.AdministradorRepository;



@Service
public class AdministradorService {
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	public Administrador crearAdministrador(Administrador administrador) {
		String contrasenaCodificada = passwordEncoder.encode(administrador.getContrasena());
		administrador.setContrasena(contrasenaCodificada);
		return administradorRepository.save(administrador);
	}
	
	public long contarAdministrador() {
		return administradorRepository.count();
	}
}
