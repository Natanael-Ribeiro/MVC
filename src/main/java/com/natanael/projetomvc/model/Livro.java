package com.natanael.projetomvc.model;

public class Livro {

	private Long id;
	private String titulo;
	private String autor;
	private String isbn;
	private Integer anoPublicacao;
	private Integer quantidadeDisponivel;

	public Livro() {
	}

	public Livro(Long id, String titulo, String autor, String isbn, Integer anoPublicacao, Integer quantidadeDisponivel) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

}
