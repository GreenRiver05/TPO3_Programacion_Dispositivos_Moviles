package com.aprendiendo.tp_3_productos.ui.salir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.aprendiendo.tp_3_productos.databinding.FragmentSalirBinding;


//1- Crear el Fragment
//2- No utilizamos el viewModel
//3- Creamos el metodo muestraDialogo()

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        muestraDialogo();
        return root;
    }


    //implementacion de Dialo mirando pildora Informativa 6 que se encuentra en el Aula
    //Instancio el dialogo y pasar el contexto (linea 44 )
    //Seteamos el titulo y el mensaje (linea 45-46)
    //Seteamos los botones de aceptar y cancelar (linea 47-54 )
    //Boton aceptar recibe 2 parametros: titulo y accion listener, adentro ponemos el fin del activity
    //Boton cancelar recibe 2 parametros: titulo y accion listener, adentro ponemos un toast con un mensaje de no salio
    //Se muestra el dialogo con el metodo show() (linea 59)

    private void muestraDialogo() {

        new AlertDialog.Builder(getContext())
                .setTitle("Salir")
                .setMessage("¿Está seguro que desea salir?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        getActivity().finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getContext(), "No Salio", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}