package com.aprendiendo.tp_3_productos.ui.cargar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aprendiendo.tp_3_productos.databinding.FragmentCargarBinding;


//1- Crear el Fragment
//2- Crear el objeto binding y el viewModel
//3- Inicializar el binding y el viewModel en el onCreateView
//4- Crear el observer para el mensaje
//5- Setear el mensaje en el textView dentro del observer
//6- Crear el listener del boton cargar
//7- Crear el metodo guardarProducto() en el viewModel
//8- llamar al metodo guardarProducto() en el listener del boton cargar

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mv = new ViewModelProvider(this).get(CargarViewModel.class);
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.twMensaje.setText("");
        mv.getmError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.twMensaje.setText(s);
                binding.twMensaje.setTextColor(Color.parseColor("#FF0000"));
            }
        });

        mv.getmExito().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                //desabilito los campos de texto, deshabilito el boton cargar y habilito el boton nuevo
                binding.twMensaje.setText(s);
                binding.twMensaje.setTextColor(Color.parseColor("#4CAF50"));
                binding.etCodigo.setEnabled(false);
                binding.etDescripcion.setEnabled(false);
                binding.etPrecio.setEnabled(false);
                binding.btCargar.setEnabled(false);
                binding.btNuevo.setEnabled(true);
                binding.btCargar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#757577")));
                binding.btNuevo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2196F3")));
            }
        });


        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtengo los valores de los campos de texto y llamo al metodo guardarProducto() en el viewModel
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescripcion.getText().toString();
                String precio = binding.etPrecio.getText().toString();
                mv.guardarProducto(codigo, descripcion, precio);
            }
        });


        binding.btNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Limpio y habilito los campos de texto, habilito el boton cargar y deshabilito el boton nuevo
                binding.etCodigo.setEnabled(true);
                binding.etDescripcion.setEnabled(true);
                binding.etPrecio.setEnabled(true);
                binding.btCargar.setEnabled(true);
                binding.btNuevo.setEnabled(false);
                binding.twMensaje.setText("");
                binding.etCodigo.setText("");
                binding.etDescripcion.setText("");
                binding.etPrecio.setText("");
                binding.etCodigo.requestFocus();
                binding.btCargar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                binding.btNuevo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#757577")));
            }
        });

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.etCodigo.setEnabled(true);
        binding.etDescripcion.setEnabled(true);
        binding.etPrecio.setEnabled(true);

        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etPrecio.setText("");
        binding.twMensaje.setText("");

        binding.btNuevo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#757577")));
        binding.btCargar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        binding.btNuevo.setEnabled(false);
        binding.btCargar.setEnabled(true);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}