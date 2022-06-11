package lc.proyecto.certificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lc.proyecto.certificacion.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
