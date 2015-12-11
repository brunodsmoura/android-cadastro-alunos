package com.example.caelum.agendaalunos.fragment.aluno;

import android.util.Log;

import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;
import com.example.caelum.agendaalunos.domain.aluno.Aluno;
import com.example.caelum.agendaalunos.util.LocalizadorUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by android5843 on 11/12/15.
 */
public class MapFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        AlunoDAO dao = new AlunoDAO(getActivity().getBaseContext());
        List<Aluno> alunos = dao.list();
        dao.close();

        if(alunos == null || alunos.isEmpty()) {
            return;
        }

        GoogleMap currentMap = getMap();

        for(Aluno aluno : alunos) {
            LatLng latLng = LocalizadorUtils.getInstance(getActivity().getBaseContext())
                                    .getCoordenada(aluno.getEndereco());

            if(latLng == null) {
                continue;
            }

            Log.i("MAPA", "Coordenadas: " + latLng);

            currentMap.addMarker(new MarkerOptions().position(latLng).title(aluno.getNome()));
        }

        LatLng caelumLatLng = new LatLng(-23.588305, -46.632395);
        currentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(caelumLatLng, 14));
    }
}
