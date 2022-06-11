package lc.proyecto.certificacion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lc.proyecto.certificacion.model.Administrador;
import lc.proyecto.certificacion.model.Postulante;
import lc.proyecto.certificacion.repository.AdministradorRepository;
import lc.proyecto.certificacion.repository.PostulanteRepository;
import lc.proyecto.certificacion.security.Usuario;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private PostulanteRepository postulanteRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Administrador> adminOpt = administradorRepository.findById(username);
		if( adminOpt.isPresent() ) {
			return new Usuario(adminOpt.get());
		} else {
			Optional<Postulante> postulanteOpt = postulanteRepository.findByRut(username);
			if( postulanteOpt.isPresent() ) {
				return new Usuario(postulanteOpt.get());
			}
		}
		throw new UsernameNotFoundException("Usuario no existe!!!");
	}
}
