package com.example.caelum.agendaalunos.fragment.aluno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caelum.agendaalunos.R;

/**
 * Created by android5843 on 10/12/15.
 */
public class DetalhesProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutDetalhesProva = inflater.inflate(R.layout.fragment_detalhe_prova, container, false);
        return layoutDetalhesProva;
    }
}
