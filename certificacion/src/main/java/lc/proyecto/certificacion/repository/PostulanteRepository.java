package lc.proyecto.certificacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lc.proyecto.certificacion.model.Postulante;

public interface PostulanteRepository extends JpaRepository<Postulante, Long>{

	public Optional<Postulante> findByRut(String rut);
}
