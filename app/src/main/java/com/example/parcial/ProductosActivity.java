package com.example.parcial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Locale;
import java.util.UUID;

public class ProductosActivity extends AppCompatActivity{
    FirebaseDatabase db;
    DatabaseReference rdb;
    private List<Producto> lista = new ArrayList<Producto>();
    ArrayAdapter<Producto> adapterprod;
    EditText name, marca, precio, stock;
    ListView lv;
    Producto p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        name = findViewById(R.id.edtnamep);
        marca = findViewById(R.id.edtmarcap);
        precio = findViewById(R.id.edtpreciop);
        stock = findViewById(R.id.edtexistp);
        lv = findViewById(R.id.lvprods);
        iniciarFireBase();
        listar();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                p = (Producto) parent.getItemAtPosition(position);
                name.setText(p.getNombre());
                marca.setText(p.getMarca());
                precio.setText(p.getPrecio());
                stock.setText(p.getExistencias());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnsave:
                validacion();
                break;
            case R.id.btnedit:
                Producto pe = new Producto();
                pe.setUid(p.getUid());
                pe.setNombre(name.getText().toString().trim());
                pe.setMarca(marca.getText().toString().trim());
                pe.setPrecio(precio.getText().toString().trim());
                pe.setExistencias(stock.getText().toString().trim());
                rdb.child("Producto").child(p.getUid()).setValue(pe);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
                limpiarcajas();
                //validaredit();
                break;
            case R.id.btndelete:
                Producto pes = new Producto();
                pes.setUid(p.getUid());
                rdb.child("Producto").child(pes.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarcajas();
                break;
        }
        return true;
    }
    public void iniciarFireBase(){
        FirebaseApp.initializeApp(this);
        db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        rdb = db.getReference();
    }
    public void validacion(){
        String nombre = name.getText().toString();
        String mark = marca.getText().toString();
        String price = precio.getText().toString();
        String existencia = stock.getText().toString();
        if(nombre.equals("")){
            name.setError("Required");
        }else{
            if (mark.equals("")){
                marca.setError("Required");
            }else {
                if (price.equals("")) {
                    precio.setError("Required");
                } else {
                    if (existencia.equals("")) {
                        stock.setError("Required");
                    } else {
                        save();
                    }
                }
            }
        }
    }
    public void save(){
        String nombre = name.getText().toString();
        String mark = marca.getText().toString();
        String price = precio.getText().toString();
        String existencia = stock.getText().toString();
        Producto p = new Producto();
        p.setUid(UUID.randomUUID().toString());
        p.setNombre(nombre);
        p.setMarca(mark);
        p.setPrecio(price);
        p.setExistencias(existencia);
        rdb.child("Producto").child(p.getUid()).setValue(p);
        Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
        limpiarcajas();
        listar();
        // redirectListView();

    }
    public void limpiarcajas(){
        name.setText("");
        marca.setText("");
        precio.setText("");
        stock.setText("");
    }
    public void listar(){
        rdb.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lista.clear();
                for(DataSnapshot objsnap : snapshot.getChildren()){
                    Producto p = objsnap.getValue(Producto.class);
                    lista.add(p);
                    adapterprod = new ArrayAdapter<Producto>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
                    lv.setAdapter(adapterprod);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}