package com.example.ucn_conectme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class grupo_work_fragment extends Fragment {

    private View fragmento_grupo_work;
    private DatabaseReference rockref, gruporef;
    private ListView grupoitem;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> ListaGrupo = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grupo_work, container, false);
        fragmento_grupo_work = view;
        rockref = FirebaseDatabase.getInstance().getReference().child("Grupos");
        grupoitem = view.findViewById(R.id.listagrupo);


        grupoitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String CurrenGrupoNombre = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getContext(), GrupoChatActivity.class);
                intent.putExtra("nombregrupo", CurrenGrupoNombre);
                startActivity(intent);

            }
        });
        LinearLayout btncreargrupo = view.findViewById(R.id.BtnCrearGrupo);


        btncreargrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                creargrupo();
            }
        });

        IniciarLista();
        MostrarListaGrupo();


        return fragmento_grupo_work;
    }


    private void creargrupo() {
        // TODO: Implementar esta función
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("nombre del grupo");
        final EditText nombregrupo = new EditText(getActivity());
        nombregrupo.setHint("nombre del grupo");
        builder.setView(nombregrupo);
        builder.setPositiveButton("crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreg = nombregrupo.getText().toString();
                if (TextUtils.isEmpty(nombreg)) {
                    Toast.makeText(getActivity(), "ingrese el nombre del grupo", Toast.LENGTH_SHORT).show();


                } else {
                    creargrupoFirebase(nombreg);

                }
            }
        }).setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void creargrupoFirebase(String nombregr) {

        rockref.child(nombregr).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "grupo creado", Toast.LENGTH_SHORT).show();

                } else {

                    String error = task.getException().getMessage().toString();
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }





    private void IniciarLista() {
        grupoitem = (ListView) fragmento_grupo_work.findViewById(R.id.listagrupo);
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ListaGrupo);
        grupoitem.setAdapter(arrayAdapter);
    }

    private void MostrarListaGrupo() {

        rockref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Set<String> set = new HashSet<>();
                Iterator iterator = snapshot.getChildren().iterator();
                while (iterator.hasNext()) {

                    set.add(((DataSnapshot) iterator.next()).getKey());

                }
                ListaGrupo.clear();
                ListaGrupo.addAll(set);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
