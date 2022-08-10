package com.edutecno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //lombok
@AllArgsConstructor //lombok
@Data //lombok
@Entity //JPA persistence indica que es una entidad (tabla en la base de datos)
@Table(name="tutorial") //JPA persistence establece el nombre de la tabla
public class Tutorial {
	
	@Id //JPA persistence indica el id en los atributos
	@GeneratedValue(strategy = GenerationType.AUTO) //JPA persistence //indica como se genera el valor del id
	private Long id;
	@Column(name="titulo",nullable = false) //JPA persistence //indica que es una columna y no puede ser null
	private String titulo;
	@Column(name="descripcion",nullable = false) //JPA persistence
	private String descripcion;
	@Column(name="publicado",nullable = false) //JPA persistence
	private boolean publicado;
	
	//constructor vacio
	//constructor con parametros
	//getters y setters
}
