package br.com.isea.centraldoacao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isea.centraldoacao.model.Usuario;
import br.com.isea.centraldoacao.service.UsuarioService;
//para Cadastro e Login

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuario));
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUsuario(@RequestBody Map<String, String> loginData) {
	    String email = loginData.get("email");
	    String senha = loginData.get("senha");

	    if (usuarioService.verificarCredenciais(email, senha)) {
	        String token = "Login Feito com Sucesso"; // Substitua por um gerador de JWT futuramente.
	        return ResponseEntity.ok(token);
	    } else {
	        return ResponseEntity.status(401).body("Credenciais inválidas");
	    }
	}
}
