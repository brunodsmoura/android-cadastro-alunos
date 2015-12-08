package com.example.caelum.agendaalunos.activity.aluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;


public class ListaAlunosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] cargaInicial = new String[] { "Alberto", "Felipe", "Carlos", "Jeferson" };
        ArrayAdapter<String> alunosAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, cargaInicial);

        ListView alunos = (ListView) findViewById(R.id.alunos);
        alunos.setAdapter(alunosAdapter);

        alunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String alunoSelecionado = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListaAlunosActivity.this, alunoSelecionado, Toast.LENGTH_LONG).show();
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
}
