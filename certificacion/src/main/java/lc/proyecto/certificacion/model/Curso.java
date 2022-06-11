package lc.proyecto.certificacion.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Curso {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	/**
	 * Cuántas personas pueden postular 
	 */
	@Min(value = 5, message = "Debe haber al menos 5 cupos disponibles")
	@Column(nullable = false)
	private int cupos;
	
	@Size(min = 1, max = 100, message = "Debe tener entre 1 y 50 caracteres")
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false, length = 300)
	private String descripcion;
		
	@Min(1)
	@Column(nullable = false)
	private int postulacionesPermitidas;
	
	@Min(0)
	@Column(nullable = false)
	private int postulacionesDisponibles;
	
	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imagen;
	
	@NotNull(message = "Debe existir una fecha de inicio")
	@Future(message = "La fecha de inicio debe ser superior al presente")
	@Column(nullable = false)
	private LocalDate postulacionesFechaInicio;
	
	@NotNull(message = "Debe existir una fecha de finalizacion")
	@Future(message = "La fecha de finalizacion debe ser superior al presente")
	@Column(nullable = false)
	private LocalDate postulacionesFechaTermino;
	
	@Transient
	@AssertTrue(message = "Campo 'Fecha Fin' debe ser una fecha posterior a 'Fecha Inicio'")
	private boolean isFechaFinMayorQueFechaInicio() {
		if(postulacionesFechaTermino != null) {
			return postulacionesFechaTermino.isAfter(postulacionesFechaInicio);
		}
		return false;
	}
}
