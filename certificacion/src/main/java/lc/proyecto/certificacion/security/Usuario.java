package lc.proyecto.certificacion.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lc.proyecto.certificacion.model.Administrador;
import lc.proyecto.certificacion.model.Postulante;

public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Administrador administrador;
	private Postulante postulante;

	/**
	 * @Contructores
	 */
	public Usuario() {
		super();
	}

	public Usuario(Administrador administrador) {		
		this.administrador = administrador;
	}

	public Usuario(Postulante postulante) {
		this.postulante = postulante;
	}

	public Usuario(Administrador administrador, Postulante postulante) {
		this.administrador = administrador;
		this.postulante = postulante;
	}

	/**
	 * @Getter and Setter
	 */
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Postulante getPostulante() {
		return postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (administrador != null)
			return List.of(new SimpleGrantedAuthority("ADMINISTRADOR"));
		if (postulante != null)
			return List.of(new SimpleGrantedAuthority("POSTULANTE"));
		return null;
	}

	@Override
	public String getPassword() {
		if (administrador != null)
			return administrador.getContrasena();
		if (postulante != null)
			return postulante.getContrasena();
		return null;
	}

	@Override
	public String getUsername() {
		if (administrador != null)
			return administrador.getUsuario();
		if (postulante != null)
			return postulante.getRut();
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
