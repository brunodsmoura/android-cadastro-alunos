package com.example.caelum.agendaalunos.adapter.aluno;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.activity.aluno.ListaAlunosActivity;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;
import com.example.caelum.agendaalunos.util.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by android5843 on 09/12/15.
 */
public class AlunoAdapter extends BaseAdapter {

    private final ListaAlunosActivity activity;
    private final List<Aluno> alunos;

    public AlunoAdapter(ListaAlunosActivity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = (alunos == null) ? new ArrayList<Aluno>() : alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return (alunos.size() <= position) ? null : alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Aluno aluno = (Aluno) getItem(position);
        return (aluno == null) ? null : aluno.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layoutItemView = inflater.inflate(R.layout.lista_item, parent, false);

        Aluno aluno = (Aluno) getItem(position);

        ImageView foto = (ImageView) layoutItemView.findViewById(R.id.foto);
        foto.setImageBitmap(ImageUtils.createBitmapImage(aluno.getCaminhoFoto(), 300));

        TextView nome = (TextView) layoutItemView.findViewById(R.id.nome);
        nome.setText(aluno.getNome());

        TextView telefone = (TextView) layoutItemView.findViewById(R.id.telefone);
        telefone.setText(aluno.getTelefone());

        int color = (position % 2) == 0 ? R.color.linha_par : R.color.linha_impar;

        layoutItemView.setBackgroundColor(activity.getResources().getColor(color));

        return layoutItemView;
    }
}
