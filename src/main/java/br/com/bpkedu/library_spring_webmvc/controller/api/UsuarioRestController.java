package br.com.bpkedu.library_spring_webmvc.controller.api;

import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAll")
    public List<Usuario> getUsuarios() {
        return usuarioService.listarTodos();
    }

    // Endpoint para cadastrar um novo usuário
    @PostMapping("/create")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // Endpoint para editar um usuário
    @PutMapping("/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.editarUsuario(id, usuario);
    }

    // Endpoint para excluir um usuário
    @DeleteMapping("/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        if (usuarioService.excluir(id)) {
            return "Usuário excluído com sucesso.";
        }
        return "Usuário não encontrado.";
    }
}

