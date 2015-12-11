package com.example.caelum.agendaalunos.fragment.aluno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.activity.aluno.ProvaActivity;
import com.example.caelum.agendaalunos.domain.aluno.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by android5843 on 10/12/15.
 */
public class ProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_prova, container, false);

        ListView listViewProvas = (ListView) fragmentView.findViewById(R.id.provas);
        List<Prova> provas = createProvas();

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity().getBaseContext(),
                                            android.R.layout.simple_list_item_1, provas);
        listViewProvas.setAdapter(adapter);
        listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada = (Prova) parent.getItemAtPosition(position);
                ProvaActivity activity = (ProvaActivity) getActivity();

                Bundle arguments = new Bundle();
                arguments.putSerializable("prova", selecionada);

                DetalhesProvaFragment detalhesFragment = new DetalhesProvaFragment();
                detalhesFragment.setArguments(arguments);

                int frameChanged = R.id.frame_prova;

                if(activity.isTablet()) {
                    frameChanged = R.id.frame_detalhe_prova;
                }

                FragmentTransaction tx = activity.getSupportFragmentManager().beginTransaction();
                tx.replace(frameChanged, detalhesFragment);
                tx.commit();
            }
        });

        return fragmentView;
    }

    private List<Prova> createProvas(){
        Prova matematica = new Prova("20/06/2015", "Matemática");
        matematica.setTopicos(Arrays.asList("Álgebra Linear", "Cálculo", "Estatística"));

        Prova portugues = new Prova("25/07/2015", "Português");
        portugues.setTopicos(Arrays.asList("Complemento Nominal, Orações Subordinadas", "Análise Sintática"));

        return Arrays.asList(matematica, portugues);
    }

}
