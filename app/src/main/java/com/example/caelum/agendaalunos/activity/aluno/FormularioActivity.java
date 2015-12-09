package com.example.caelum.agendaalunos.activity.aluno;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;
import com.example.caelum.agendaalunos.helper.aluno.FormularioHelper;

/**
 * Created by android5843 on 07/12/15.
 */
public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        this.helper = new FormularioHelper(this);

        if(getIntent().hasExtra(ListaAlunosActivity.EXTRA_ALUNO_SELECIONADO)) {
            Aluno alunoSelecionado = (Aluno) getIntent().getSerializableExtra(ListaAlunosActivity.EXTRA_ALUNO_SELECIONADO);

            this.helper.showAluno(alunoSelecionado);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_formulario_alunos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.form_menu_salvar) {
            Aluno novoAluno = helper.toAluno();
            AlunoDAO dao = new AlunoDAO(this);

            long id = 0l;
            String mensagemSucesso = null;

            if(helper.validate()) {

                if(novoAluno.getId() == null) {
                    id = dao.insert(novoAluno);
                    mensagemSucesso = String.format("Aluno: %d - %s inserido com sucesso", id, novoAluno.getNome());
                } else {
                    dao.update(novoAluno);

                    id = novoAluno.getId();
                    mensagemSucesso = String.format("Aluno: %d - %s atualizado com sucesso", id, novoAluno.getNome());
                }

                dao.close();

                Toast.makeText(FormularioActivity.this, mensagemSucesso,Toast.LENGTH_LONG).show();

                finish();
            }
        }

        return true;
    }
}
