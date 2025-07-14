package com.aquirez.tarefafinal.entidade;

public class livro {
    private long id;
    private String titulo, autor, capaUri, descricao;

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(long id) {
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

    public String getCapaUri() {
        return capaUri;
    }

    public void setCapaUri(String capaUri) {
        this.capaUri = capaUri;
    }
}