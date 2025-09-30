package com.aprendiendo.tp_3_productos.ui.listar;

import static com.aprendiendo.tp_3_productos.MainActivity.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendiendo.tp_3_productos.databinding.FragmentListarBinding;
import com.aprendiendo.tp_3_productos.modelo.Producto;

import java.util.List;

//1- Crear el Fragment
//2- Crear el objeto binding y el viewModel
//3- Inicializar el binding y el viewModel en el onCreateView
//4- Crear el observer para la lista de productos y el mensaje
//5- Crear el adapter y el layoutManager dentro del observer
//6- Setear el adapter y el layoutManager en el recyclerView
//7- Crear el metodo cargarLista() en el viewModel
//8- llamar al metodo cargarLista() en el onCreateView

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(ListarViewModel.class);
        View root = binding.getRoot();


        //Creo el observer para el mensaje
        mv.getmMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
               Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        //Creo el observer para la lista de productos
        mv.getLista().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                //Crear un objeto adapter con la lista de productos pasandole el context, el inflater y la lista
                ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());

                //Crear un objeto layoutManager con el context y el orientation vertical
                GridLayoutManager glm = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
                binding.rvLista.setLayoutManager(glm);
                binding.rvLista.setAdapter(adapter);
            }
        });

        mv.cargarLista();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}