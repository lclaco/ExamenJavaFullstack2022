package lc.proyecto.certificacion.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lc.proyecto.certificacion.model.Postulacion;
import lc.proyecto.certificacion.model.Postulante;


public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {

public long countByPostulante(Postulante postulante);
	
	public List<Postulacion> findByPostulante(Postulante postulante);
	
}