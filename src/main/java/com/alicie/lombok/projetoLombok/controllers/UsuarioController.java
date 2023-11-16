package com.alicie.lombok.projetoLombok.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alicie.lombok.projetoLombok.entities.Usuario;
import com.alicie.lombok.projetoLombok.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public Usuario creatUsuario(@RequestBody Usuario usuario) {
		return usuarioService.insertUsuario(usuario);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
		Usuario usuario = usuarioService.findUsuarioById(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}
	
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuario(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Usuario> usuario = usuarioService.findAllUsuario();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType).body(usuario);
	}
	
	@PutMapping("/{id}")
	public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(id, usuario);
	}
	
}
	
