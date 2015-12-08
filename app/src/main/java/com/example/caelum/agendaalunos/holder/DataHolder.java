package com.example.caelum.agendaalunos.holder;

import com.example.caelum.agendaalunos.domain.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5843 on 08/12/15.
 */
public final class DataHolder {

    private static DataHolder _instance;
    private final List<Aluno> alunos;

    private DataHolder() {
        this.alunos = new ArrayList<>();
    }

    public static DataHolder getInstance() {
        if(_instance == null) {
            _instance = new DataHolder();
        }

        return _instance;
    }

    public void add(Aluno aluno) {
        int nextId = alunos.size() + 1;

        aluno.setId(Long.valueOf(nextId));

        this.alunos.add(aluno);
    }

    public boolean delete(Aluno aluno) {
        if(aluno == null && aluno.getId() == null) {
            return false;
        }

        return this.alunos.remove(aluno.getId() - 1);
    }
}
