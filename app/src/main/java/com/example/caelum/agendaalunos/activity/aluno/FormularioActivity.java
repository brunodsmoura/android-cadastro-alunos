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
            if(helper.validate()) {
                Aluno novoAluno = helper.toAluno();
                AlunoDAO dao = new AlunoDAO(this);

                long id = dao.insert(novoAluno);
                dao.close();

                Toast.makeText(FormularioActivity.this, String.format("Aluno: %d - %s inserido com sucesso",
                                id, novoAluno.getNome()),Toast.LENGTH_LONG).show();

                finish();
            }
        }

        return true;
    }
}
