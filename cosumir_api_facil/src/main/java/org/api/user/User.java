package org.api.user;

public class User {

    private String nome;
    private String username;
    private String email;
    private String cell;
    private String pais;
    private String cidade;


    public User(String nome, String username, String email, String cell, String pais, String cidade) {

        this.nome = nome;
        this.username = username;
        this.email = email;
        this.cell = cell;
        this.pais = pais;
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

}
