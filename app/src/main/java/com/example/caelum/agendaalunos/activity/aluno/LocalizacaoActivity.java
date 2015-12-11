package com.example.caelum.agendaalunos.activity.aluno;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.fragment.aluno.MapFragment;

/**
 * Created by android5843 on 11/12/15.
 */
public class LocalizacaoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_localizacao);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_mapa_localizacao, new MapFragment());
        tx.commit();
    }

}
