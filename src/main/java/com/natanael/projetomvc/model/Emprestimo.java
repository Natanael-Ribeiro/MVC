package com.natanael.projetomvc.model;

import java.time.LocalDate;

public class Emprestimo {

	private Long id;
	private Livro livro;
	private Usuario usuario;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucaoPrevista;
	private boolean devolvido;

	public Emprestimo() {
	}

	public Emprestimo(Long id, Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, boolean devolvido) {
		this.id = id;
		this.livro = livro;
		this.usuario = usuario;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.devolvido = devolvido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

}
