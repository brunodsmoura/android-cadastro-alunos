package com.example.caelum.agendaalunos.task.async.aluno;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.caelum.agendaalunos.client.aluno.AlunoClient;
import com.example.caelum.agendaalunos.converter.aluno.AlunoConverter;
import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5843 on 10/12/15.
 */
public class AlunoAsync extends AsyncTask<Void, Object, String> {

    private final Context context;

    private AlertDialog progressDialog;

    public AlunoAsync(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new AlertDialog.Builder(context)
                                .setMessage("Aguarde enquanto efetuamos o cálculo...")
                                .setTitle("Tarefa em execução")
                                .show();
    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonAlunos = new AlunoConverter().toJson(retrieveAlunos(true));
        return new AlunoClient().send(jsonAlunos);
    }

    private List<Aluno> retrieveAlunos(boolean production) {
        List<Aluno> alunos = null;

        if(production) {
            AlunoDAO dao = new AlunoDAO(context);
            alunos = dao.list();
            dao.close();
        } else {
            alunos = new ArrayList<Aluno>();
            Aluno auxiliar = null;

            for(int i = 0; i < 5000; i++) {
                auxiliar = new Aluno();
                auxiliar.setNome(String.format("Aluno %d", i));
                auxiliar.setNota(8d);

                alunos.add(auxiliar);
            }
        }

        return alunos;
    }

    @Override
    protected void onPostExecute(String response) {
        progressDialog.dismiss();

        if(response != null && !response.isEmpty()) {
            JSONObject parsedResponse = null;

            try {
                parsedResponse = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(context, "Erro ao traduzir resposta do servidor.", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(context, String.format("A média das notas da classe é: %s - Total de Alunos: %s",
                            parsedResponse.optString("media", "0"), parsedResponse.optString("quantidade", "0")),
                            Toast.LENGTH_LONG).show();
        }

    }

}