package br.com.bpkedu.library_spring_webmvc.controller;

import br.com.bpkedu.library_spring_webmvc.domain.Usuario;
import br.com.bpkedu.library_spring_webmvc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Exibir a lista de usuários
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listar";  // Nome do template Thymeleaf
    }

    // Exibir o formulário de cadastro de usuário
    @GetMapping("/cadastrar")
    public String cadastrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());  // Criando um novo usuário
        return "usuarios/cadastrar";  // Nome do template Thymeleaf
    }

    // Cadastrar um novo usuário (via POST)
    @PostMapping("/cadastrar")
    public String salvarUsuario(Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios/listar";  // Redirecionar para a lista após salvar
    }

    // Exibir o formulário de edição de usuário
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarios/editar";  // Nome do template Thymeleaf
        }
        return "redirect:/usuarios/listar";  // Se o usuário não for encontrado, redireciona para a lista
    }

    // Editar o usuário (via POST)
    @PostMapping("/editar/{id}")
    public String atualizarUsuario(@PathVariable Long id, Usuario usuario) {
        usuario.setId(id);  // Atualiza o ID do usuário
        usuarioService.editarUsuario(id, usuario);  // Chama o serviço para atualizar o usuário no banco de dados
        return "redirect:/usuarios/listar";  // Redirecionar para a lista após editar
    }

    // Excluir o usuário
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        usuarioService.excluir(id);  // Chama o serviço para excluir o usuário
        return "redirect:/usuarios/listar";  // Redirecionar para a lista após excluir
    }
}

