package com.edutecno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutecno.model.Tutorial;
import com.edutecno.repository.TutorialRepository;

@Service//anotacion para configurar la clase como servicio en Spring
public class TutorialServiceImp implements TutorialService {
	
	@Autowired//inyeccion de dependencias del repositorio
	private TutorialRepository tutorialRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tutorial> findAll() {
		return tutorialRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tutorial> findByTitulo(String titulo) {
		return tutorialRepository.findByTituloIgnoreCase(titulo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tutorial> findById(Long id) {
		return tutorialRepository.findById(id);
	}

	@Override
	@Transactional
	public Tutorial save(Tutorial tutorial) {		
		return tutorialRepository.save(tutorial);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		tutorialRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteAll() {
		tutorialRepository.deleteAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tutorial> findByPublicado(boolean publicado) {
		return tutorialRepository.findByPublicado(publicado);
	}
}
