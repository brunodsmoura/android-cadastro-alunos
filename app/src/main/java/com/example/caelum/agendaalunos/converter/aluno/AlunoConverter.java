package com.example.caelum.agendaalunos.converter.aluno;

import com.example.caelum.agendaalunos.domain.aluno.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by android5843 on 10/12/15.
 */
public class AlunoConverter {

    public String toJson(List<Aluno> alunos) {
        if(alunos == null || alunos.isEmpty()) {
            return null;
        }

        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object().key("list").array();
            jsonStringer.object().key("aluno").array();

            for (Aluno aluno : alunos) {

                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("endereco").value(aluno.getEndereco())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getNota())
                        .endObject();

            }

            jsonStringer.endArray().endObject();
            jsonStringer.endArray().endObject();

            return jsonStringer.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}