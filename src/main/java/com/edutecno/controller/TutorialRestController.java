package com.edutecno.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edutecno.model.Tutorial;
import com.edutecno.service.TutorialService;

@RestController//un controlador de tipo Rest
@RequestMapping("/api") //endpoint, mapping o llave de entrada para el controlador
public class TutorialRestController {
	
	@Autowired //inyeccion de dependencias del servicio
	private TutorialService tutorialService;
	
	//getAllTutorial
	@GetMapping("/tutoriales")
	//@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<Tutorial>> getAllTutorial(@RequestParam(required = false) String titulo){
		
		try {
			List<Tutorial> tutoriales = new ArrayList<Tutorial>();//almacenar los tutoriales consultados u obtenidos mediante el repositorio
			
			if (titulo == null) {
//				tutoriales = tutorialService.findAll();
				tutorialService.findAll().forEach(tutoriales::add);
			} else {
				tutoriales = tutorialService.findByTitulo(titulo);
			}
			
			if (tutoriales.isEmpty()) {//si la lista de tutoriales esta vacia
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutoriales, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//getTutorialById
	@GetMapping("/tutoriales/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id){
		
		try {
			Optional<Tutorial> tutorialEncontrado = tutorialService.findById(id);
			
			if (tutorialEncontrado.isPresent()) {//si el objeto esta presente
				return new ResponseEntity<>(tutorialEncontrado.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//createTutorial
	@PostMapping("/tutoriales")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
		
		try {
			if (tutorial.getTitulo() != null && tutorial.getDescripcion() != null) {
				Tutorial tutorialGuardado = tutorialService.save(tutorial);
				return new ResponseEntity<>(tutorialGuardado, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//updateTutorial
	@PutMapping("/tutoriales/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id")Long id, @RequestBody Tutorial tutorial){
		
		try {
			Optional<Tutorial> tutorialEncontrado = tutorialService.findById(id);
			
			if (tutorialEncontrado.isPresent()) {
				
				Tutorial tutorialModificado = tutorialEncontrado.get();//tutorial encontrado es opcional y se obtiene su valor con .get()
				tutorialModificado.setTitulo(tutorial.getTitulo());
				tutorialModificado.setDescripcion(tutorial.getDescripcion());
				tutorialModificado.setPublicado(tutorial.isPublicado());
				
				return new ResponseEntity<Tutorial>(tutorialService.save(tutorialModificado), HttpStatus.OK);	
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//deleteTutorial
	@DeleteMapping("/tutoriales/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id")Long id){
		
		try {
			Optional<Tutorial> tutorialEncontrado =  tutorialService.findById(id);
			
			if (tutorialEncontrado.isPresent()) {
				tutorialService.deleteById(id);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//deleteAllTutorial
	@DeleteMapping("/tutoriales")
	public ResponseEntity<HttpStatus> deleteAllTutorial(){
		
		try {
			tutorialService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//findByPublicado
	@GetMapping("/tutoriales/publicados")
	public ResponseEntity<List<Tutorial>> findByPublicado(){
		
		try {
			List<Tutorial> tutorialesPublicados =  tutorialService.findByPublicado(true);
			
			if (tutorialesPublicados.isEmpty()) {//si la lista esta vacia
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Tutorial>>(tutorialesPublicados, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
