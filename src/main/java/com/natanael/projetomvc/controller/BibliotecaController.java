package com.natanael.projetomvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.natanael.projetomvc.model.Emprestimo;
import com.natanael.projetomvc.model.Livro;
import com.natanael.projetomvc.model.Usuario;

@Controller
public class BibliotecaController {

	private List<Livro> livros = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Emprestimo> emprestimos = new ArrayList<>();

	private Long proximoIdLivro = 1L;
	private Long proximoIdUsuario = 1L;
	private Long proximoIdEmprestimo = 1L;

	public BibliotecaController() {
		livros.add(new Livro(proximoIdLivro++, "Dom Casmurro", "Machado de Assis", "9788535914849", 1899, 3));
		livros.add(new Livro(proximoIdLivro++, "O Cortico", "Aluisio Azevedo", "9788508151233", 1890, 2));

		usuarios.add(new Usuario(proximoIdUsuario++, "Natanael Ribeiro", "natanael@email.com", "2026001"));
		usuarios.add(new Usuario(proximoIdUsuario++, "Carlos Silva", "carlos@email.com", "2026002"));
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("totalLivros", livros.size());
		model.addAttribute("totalUsuarios", usuarios.size());
		model.addAttribute("totalEmprestimos", emprestimos.size());
		return "index";
	}

	@GetMapping("/livros")
	public String listarLivros(Model model) {
		model.addAttribute("livros", livros);
		return "livros/lista";
	}

	@GetMapping("/livros/novo")
	public String novoLivroForm(Model model) {
		model.addAttribute("livro", new Livro());
		return "livros/form";
	}

	@PostMapping("/livros/salvar")
	public String salvarLivro(@ModelAttribute Livro livro) {
		if (livro.getId() == null) {
			livro.setId(proximoIdLivro++);
			if (livro.getQuantidadeDisponivel() == null) {
				livro.setQuantidadeDisponivel(1);
			}
			livros.add(livro);
		} else {
			for (int i = 0; i < livros.size(); i++) {
				if (livros.get(i).getId().equals(livro.getId())) {
					livros.set(i, livro);
					break;
				}
			}
		}
		return "redirect:/livros";
	}

	@GetMapping("/livros/editar/{id}")
	public String editarLivroForm(@PathVariable Long id, Model model) {
		for (Livro l : livros) {
			if (l.getId().equals(id)) {
				model.addAttribute("livro", l);
				return "livros/form";
			}
		}
		return "redirect:/livros";
	}

	@GetMapping("/livros/excluir/{id}")
	public String excluirLivro(@PathVariable Long id) {
		livros.removeIf(l -> l.getId().equals(id));
		return "redirect:/livros";
	}

	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios", usuarios);
		return "usuarios/lista";
	}

	@GetMapping("/usuarios/novo")
	public String novoUsuarioForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuarios/form";
	}

	@PostMapping("/usuarios/salvar")
	public String salvarUsuario(@ModelAttribute Usuario usuario) {
		if (usuario.getId() == null) {
			usuario.setId(proximoIdUsuario++);
			usuarios.add(usuario);
		} else {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getId().equals(usuario.getId())) {
					usuarios.set(i, usuario);
					break;
				}
			}
		}
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/editar/{id}")
	public String editarUsuarioForm(@PathVariable Long id, Model model) {
		for (Usuario u : usuarios) {
			if (u.getId().equals(id)) {
				model.addAttribute("usuario", u);
				return "usuarios/form";
			}
		}
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/excluir/{id}")
	public String excluirUsuario(@PathVariable Long id) {
		usuarios.removeIf(u -> u.getId().equals(id));
		return "redirect:/usuarios";
	}

	@GetMapping("/emprestimos")
	public String listarEmprestimos(Model model) {
		model.addAttribute("emprestimos", emprestimos);
		return "emprestimos/lista";
	}

	@GetMapping("/emprestimos/novo")
	public String novoEmprestimoForm(Model model) {
		model.addAttribute("livros", livros);
		model.addAttribute("usuarios", usuarios);
		return "emprestimos/form";
	}

	@PostMapping("/emprestimos/salvar")
	public String salvarEmprestimo(@RequestParam Long livroId, @RequestParam Long usuarioId, @RequestParam(defaultValue = "7") int dias) {
		Livro livroSelecionado = null;
		for (Livro l : livros) {
			if (l.getId().equals(livroId)) {
				livroSelecionado = l;
				break;
			}
		}

		Usuario usuarioSelecionado = null;
		for (Usuario u : usuarios) {
			if (u.getId().equals(usuarioId)) {
				usuarioSelecionado = u;
				break;
			}
		}

		if (livroSelecionado != null && usuarioSelecionado != null && livroSelecionado.getQuantidadeDisponivel() > 0) {
			livroSelecionado.setQuantidadeDisponivel(livroSelecionado.getQuantidadeDisponivel() - 1);
			Emprestimo emprestimo = new Emprestimo(
					proximoIdEmprestimo++,
					livroSelecionado,
					usuarioSelecionado,
					LocalDate.now(),
					LocalDate.now().plusDays(dias),
					false
			);
			emprestimos.add(emprestimo);
		}

		return "redirect:/emprestimos";
	}

	@GetMapping("/emprestimos/devolver/{id}")
	public String devolverEmprestimo(@PathVariable Long id) {
		for (Emprestimo e : emprestimos) {
			if (e.getId().equals(id) && !e.isDevolvido()) {
				e.setDevolvido(true);
				if (e.getLivro() != null) {
					e.getLivro().setQuantidadeDisponivel(e.getLivro().getQuantidadeDisponivel() + 1);
				}
				break;
			}
		}
		return "redirect:/emprestimos";
	}

}
