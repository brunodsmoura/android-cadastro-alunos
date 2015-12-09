package com.example.caelum.agendaalunos.helper.aluno;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.activity.aluno.FormularioActivity;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;

/**
 * Created by android5843 on 08/12/15.
 */
public class FormularioHelper {

    private final FormularioActivity activity;

    private final EditText nome;
    private final EditText telefone;
    private final EditText endereco;
    private final EditText site;
    private final RatingBar nota;
    private Long id;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;

        this.nome = (EditText) activity.findViewById(R.id.form_nome);
        this.telefone = (EditText) activity.findViewById(R.id.form_telefone);
        this.endereco = (EditText) activity.findViewById(R.id.form_endereco);
        this.site = (EditText) activity.findViewById(R.id.form_site);
        this.nota = (RatingBar) activity.findViewById(R.id.form_nota);
    }

    public Aluno toAluno(){
        Aluno novoAluno = new Aluno();

        novoAluno.setId(id);
        novoAluno.setNome(String.valueOf(nome.getText()));
        novoAluno.setTelefone(String.valueOf(telefone.getText()));
        novoAluno.setEndereco(String.valueOf(endereco.getText()));
        novoAluno.setSite(String.valueOf(site.getText()));
        novoAluno.setNota(Double.valueOf(nota.getRating()));

        return novoAluno;
    }

    public void showAluno(Aluno aluno) {
        if(aluno == null || aluno.getId() == null) {
            throw new NullPointerException("Aluno não pode ser nulo.");
        }

        id = aluno.getId();
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());

        if(aluno.getNota() != null) {
            nota.setRating(aluno.getNota().floatValue());
        }
    }

    public boolean validate(){
        String valorNome = nome.getText().toString().trim().replaceAll("\\s+", " ");

        if(valorNome == null || valorNome.isEmpty()) {
            nome.setError("Favor verificar o preenchimento do campo: Nome");
            return false;
        }

        return true;
    }

}
