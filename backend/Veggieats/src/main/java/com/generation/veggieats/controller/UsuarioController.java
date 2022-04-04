package com.generation.veggieats.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.veggieats.model.Usuario;
import com.generation.veggieats.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	public UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> GetByNome (@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<Usuario>> GetByCpf (@PathVariable String cpf){
		return ResponseEntity.ok(repository.findAllByCpfContainingIgnoreCase(cpf));
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Usuario>> GetByEmail (@PathVariable String email){
		return ResponseEntity.ok(repository.findAllByEmailContainingIgnoreCase(email));
	}
	@GetMapping("/tipoUsuario/{tipoUsuario}")
	public ResponseEntity<List<Usuario>> GetByTipoUsuario (@PathVariable String tipoUsuario){
		return ResponseEntity.ok(repository.findAllByTipoUsuarioContainingIgnoreCase(tipoUsuario));
	}
	
	//---------------MÉTODO POST--------------
	
	@PostMapping
	public ResponseEntity<Usuario> post (@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}
	
	//---------------MÉTODO PUT--------------
	
	@PutMapping
	public ResponseEntity<Usuario> put (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
	
	//---------------MÉTODO DELETE------------
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
