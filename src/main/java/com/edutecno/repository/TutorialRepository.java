package com.edutecno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutecno.model.Tutorial;

@Repository //anotacion para indicar y configurar en Spring el repositorio
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	public List<Tutorial> findByPublicado(boolean publicado);
	public List<Tutorial> findByTituloIgnoreCase(String titulo);
	//public List<Tutorial> findByTituloAndPublicado(String titulo, boolean publicado);
}
