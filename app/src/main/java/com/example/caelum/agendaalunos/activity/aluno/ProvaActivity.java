package com.example.caelum.agendaalunos.activity.aluno;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.fragment.aluno.ProvaFragment;

/**
 * Created by android5843 on 10/12/15.
 */
public class ProvaActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_prova);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_prova, new ProvaFragment());
        transaction.commit();
    }
}
