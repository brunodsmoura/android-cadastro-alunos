package com.example.caelum.agendaalunos.domain.aluno;

import java.io.Serializable;
import java.util.List;

/**
 * Created by android5843 on 10/12/15.
 */
public class Prova implements Serializable {

    private String nome;
    private String data;
    private List<String> topicos;

    public Prova(String data, String nome) {
        this.data = data;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return nome;
    }
}
