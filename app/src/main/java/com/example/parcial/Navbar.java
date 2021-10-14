package com.example.parcial;

import android.app.Activity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Navbar extends Fragment {
    private final int[] BOTONESMENU={R.id.cvproductos,R.id.cvventas,R.id.cvreportes};
    CardView card;
    View vista;
    public Navbar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_navbar, container, false);
        card=vista.findViewById(R.id.cvproductos);
        for (int i=0;i<BOTONESMENU.length;i++){
            card=(CardView) vista.findViewById(BOTONESMENU[i]);
            final int queboton=i;
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Bien",Toast.LENGTH_SHORT).show();
                    Activity thisactivity=getActivity();
                    ((ComunicaMenu)thisactivity).menu(queboton);
                }
            });
        }

        return vista;
    }
}