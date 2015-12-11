package com.example.caelum.agendaalunos.fragment.aluno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.domain.aluno.Prova;

import java.io.Serializable;

/**
 * Created by android5843 on 10/12/15.
 */
public class DetalhesProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutDetalhesProva = inflater.inflate(R.layout.fragment_detalhe_prova, container, false);
        Bundle parametros = getArguments();

        if(parametros != null) {
            Prova provaSelecionada = (Prova) parametros.getSerializable("prova");

            TextView nomeMateria = (TextView) layoutDetalhesProva.findViewById(R.id.detalhe_prova_materia);
            nomeMateria.setText(provaSelecionada.getNome());

            TextView dataMateria = (TextView) layoutDetalhesProva.findViewById(R.id.detalhe_prova_data);
            dataMateria.setText(provaSelecionada.getData());

            ListView topicosMateria = (ListView) layoutDetalhesProva.findViewById(R.id.detalhe_prova_topicos);

            ArrayAdapter<String> adapterTopicos = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, provaSelecionada.getTopicos());
            topicosMateria.setAdapter(adapterTopicos);
        }

        return layoutDetalhesProva;
    }
}
