package com.example.parcial;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial.model.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SpaceOperations extends Fragment {
    FirebaseDatabase dba;
    DatabaseReference rdba;
    View vista;
    Button btn;
    private List<Producto> lista = new ArrayList<Producto>();
    ArrayAdapter<Producto> adapterprod;
    ListView listv;
    public SpaceOperations() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_space_operations, container, false);
        listv = vista.findViewById(R.id.lvprods);
      //  iniciarFireBase();
        //listar();
        btn = vista.findViewById(R.id.btnsave);
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // prueba();
                validacion();
            }
        });*/
        return vista;
    }


}