package lc.proyecto.certificacion.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lc.proyecto.certificacion.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, String>{

	
}
