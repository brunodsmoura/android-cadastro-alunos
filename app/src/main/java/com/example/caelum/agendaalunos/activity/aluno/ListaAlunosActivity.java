package com.example.caelum.agendaalunos.activity.aluno;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.adapter.aluno.AlunoAdapter;
import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;

import java.util.List;


public class ListaAlunosActivity extends ActionBarActivity {

    public static final String EXTRA_ALUNO_SELECIONADO = "alunoSelecionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        ListView listaAlunos = (ListView) findViewById(R.id.alunos);
        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAlunos();
    }

    private void loadAlunos(){
        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> dadosAluno = dao.list();
        dao.close();

        AlunoAdapter alunosAdapter = new AlunoAdapter(this, dadosAluno);

        ListView alunos = (ListView) findViewById(R.id.alunos);
        alunos.setAdapter(alunosAdapter);

        alunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                edicao.putExtra(EXTRA_ALUNO_SELECIONADO, aluno);

                startActivity(edicao);
            }
        });

        alunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, String.format(">> Posição selecionada: %d",
                        (position + 1)),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button botaoNovo = (Button) findViewById(R.id.botao_novo);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novo = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(novo);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo adapterInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ListView listaAlunos = (ListView) findViewById(R.id.alunos);

        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(adapterInfo.position);

        MenuItem deleteAction = menu.add("Deletar");
        deleteAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);

                                dao.delete(aluno);
                                dao.close();

                                Toast.makeText(ListaAlunosActivity.this, String.format("Aluno %s excluído com sucesso!", aluno.getNome()),
                                        Toast.LENGTH_LONG).show();

                                loadAlunos();
                            }
                        }).setNegativeButton("Não", null).show();

                return true;
            }
        });

        // FUNCIONALIDADE PARA FAZER UMA LIGACAO
        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_CALL);

        intentLigar.setData(Uri.parse(String.format("tel:%s", aluno.getTelefone())));
        ligar.setIntent(intentLigar);

        // FUNCIONALIDADE PARA ABRIR O MAPA
        MenuItem abrirMapa = menu.add("Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);

        intentMapa.setData(Uri.parse(String.format("geo:%s,%s&z=14&q=%s", "0", "0", aluno.getEndereco())));
        abrirMapa.setIntent(intentMapa);

        // FUNCIONALIDADE PARA ABRIR O BROWSER
        MenuItem navegar = menu.add("Navegar no site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        intentSite.setData(Uri.parse("http:"+aluno.getSite()));
        navegar.setIntent(intentSite);
    }
}
