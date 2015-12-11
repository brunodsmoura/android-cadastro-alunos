package com.example.caelum.agendaalunos.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by android5843 on 11/12/15.
 */
public class LocalizadorUtils {
    private static LocalizadorUtils _instance = null;
    private Geocoder geo;

    private LocalizadorUtils(Context context) {
        geo = new Geocoder(context, Locale.getDefault());
    }

    public static LocalizadorUtils getInstance(Context context){
        if(_instance == null) {
            _instance = new LocalizadorUtils(context);
        }

        return _instance;
    }

    public LatLng getCoordenada(String endereco) {
        if(endereco == null || endereco.isEmpty()) {
            return null;
        }

        List<Address> addresses = null;

        try {
            addresses = geo.getFromLocationName(endereco, 1);

            if(addresses == null || addresses.isEmpty()) {
                return null;
            }

            Address firstAddress = addresses.get(0);
            return new LatLng(firstAddress.getLatitude(), firstAddress.getLongitude());
        } catch(IOException cause) {
            Log.e("MAPA", "Problemas ao recuperar os dados de latitude/longitude do endereco: " + endereco);
            return null;
        }
    }
}
