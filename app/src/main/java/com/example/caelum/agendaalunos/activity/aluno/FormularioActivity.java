package com.example.caelum.agendaalunos.activity.aluno;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;
import com.example.caelum.agendaalunos.helper.aluno.FormularioHelper;

import java.io.File;

/**
 * Created by android5843 on 07/12/15.
 */
public class FormularioActivity extends ActionBarActivity {

    private static int INTENT_TAKE_PICTURE_ID = 123;

    private FormularioHelper helper;

    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        this.helper = new FormularioHelper(this);

        if(getIntent().hasExtra(ListaAlunosActivity.EXTRA_ALUNO_SELECIONADO)) {
            Aluno alunoSelecionado = (Aluno) getIntent().getSerializableExtra(ListaAlunosActivity.EXTRA_ALUNO_SELECIONADO);

            this.helper.showAluno(alunoSelecionado);
        }

        Button botaoTirarFoto = this.helper.getBotaoTirarFoto();
        botaoTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoPath = String.format("%s/%d.jpg", getExternalFilesDir(null), System.currentTimeMillis());

                Intent tirarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));

                startActivityForResult(tirarFoto, INTENT_TAKE_PICTURE_ID);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_TAKE_PICTURE_ID) {
            if(resultCode == Activity.RESULT_OK) {
                this.helper.showPhoto(this.photoPath);
            } else {
                this.photoPath = null;
            }
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
            Aluno alunoAuxiliar = helper.toAluno();
            AlunoDAO dao = new AlunoDAO(this);

            if(helper.validate()) {
                long id = dao.insertOrUpdate(alunoAuxiliar);
                dao.close();

                Toast.makeText(FormularioActivity.this, String.format("Aluno: %d - %s persistido com sucesso",
                        id, alunoAuxiliar.getNome()),Toast.LENGTH_LONG).show();

                finish();
            }
        }

        return true;
    }
}
