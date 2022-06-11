package lc.proyecto.certificacion;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lc.proyecto.certificacion.model.Administrador;
import lc.proyecto.certificacion.model.Curso;
import lc.proyecto.certificacion.model.Postulante;
import lc.proyecto.certificacion.repository.CursoRepository;
import lc.proyecto.certificacion.service.AdministradorService;
import lc.proyecto.certificacion.service.PostulanteService;



@SpringBootApplication
public class CertificacionApplication {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired 
	PostulanteService postulanteService;
	
	public static void main(String[] args) {
		SpringApplication.run(CertificacionApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService administradorService, CursoRepository cursoRepository ) {
		return (args) -> {
			if( administradorService.contarAdministrador() == 0) {
				Administrador administrador = Administrador.builder()
						.usuario("admin")
						.contrasena("1234")
						.build();
				administradorService.crearAdministrador(administrador);
			}
			if(cursoRepository.count()==0) {
				Curso curso = Curso.builder() 
										.cupos(50)
										.titulo("Comercio Electronico Y Marketing Digital")
										.descripcion("La ocupación está enfocada en potencia "
												+ "/n y fomentar la realización de ventas por "
												+ "/n medios digitales y la aplicación de técnicas"
												+ "/n comunicativas de marketing digital dentro "
												+ "/n de un contexto comercial.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/ComercioElectronicoYMarketingDigital.webp")))
										.postulacionesPermitidas(200)
										.postulacionesDisponibles(100)
										.postulacionesFechaInicio( LocalDate.of(2022, 10, 10) )
										.postulacionesFechaTermino( LocalDate.of(2023, 7, 10) )
										.build()
				;
				cursoRepository.save(curso);
								
				curso = Curso.builder() 
						.cupos(20)
						.titulo("Procesos Logisticos En Bodegas, Centros De Distribucion Y Centros De Transferencia")
						.descripcion("El Asistente de Logística operativa"
								+ "/n podrá desempeñar sus funciones recepción, "
								+ "/n almacenaje, preparación de pedidos y despacho "
								+ "/n de mercancías, pudiendo desempeñarse en "
								+ "/n grandes, medianas y pequeñas empresas logísticas")
						.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/ProcesosLogisticosEnBodega.webp")))
						.postulacionesPermitidas(100)
						.postulacionesDisponibles(90)
						.postulacionesFechaInicio( LocalDate.of(2022, 10, 10) )
						.postulacionesFechaTermino( LocalDate.of(2023, 7, 30) )
						.build()
				;
				cursoRepository.save(curso);
			}
				if(postulanteService.contarPostulantes() == 0) {
				Postulante postulante = Postulante.builder()
										.rut("99888777-k")
										.nombre("Alan")
										.apellidoPaterno("Brito")
										.apellidoMaterno("Delgado")
										.direccion("bla bla 1212")
										.telefono("987456321")
										.email("contacto@prueba.cl")
										.region("Los Rios")
										.comuna("Panguipulli")
										.contrasena("4321")
										.build()
				;
				postulanteService.crearPostulante(postulante);
				
				postulante = Postulante.builder()
						.rut("77888999-k")
						.nombre("Alan")
						.apellidoPaterno("Brito")
						.apellidoMaterno("Grueso")
						.direccion("bla bla 1212")
						.telefono("987456321")
						.email("contacto@prueba.cl")
						.region("Los Rios")
						.comuna("Panguipulli")
						.contrasena("4321")
						.build()
				;
				
				postulanteService.crearPostulante(postulante);
			}
		};
	}
}