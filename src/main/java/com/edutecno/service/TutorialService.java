package com.edutecno.service;

import java.util.List;
import java.util.Optional;

import com.edutecno.model.Tutorial;

public interface TutorialService {
	
	public List<Tutorial> findAll();
	public List<Tutorial> findByTitulo(String titulo);
	public Optional<Tutorial> findById(Long id);
	public Tutorial save(Tutorial tutorial); //save realiza la creación como la actualizacion del Tutorial
	public void deleteById(Long id);
	public void deleteAll();
	public List<Tutorial> findByPublicado(boolean publicado);
	
}
