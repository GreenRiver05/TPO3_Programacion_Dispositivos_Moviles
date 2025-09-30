package com.aprendiendo.tp_3_productos.ui.listar;

import static com.aprendiendo.tp_3_productos.MainActivity.*;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aprendiendo.tp_3_productos.modelo.Producto;

import java.util.Comparator;
import java.util.List;

//1- Crear el ViewModel
//2- Crear el MutableLiveData
//3- Crear el getLista() para instanciar el MutableLiveData
//4- Crear el cargarLista() para cargar la lista de productos
//5- Setear el MutableLiveData con la lista de productos ordenada
public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<List<Producto>> mListaMutable;
    private MutableLiveData<String> mMensaje;

    public ListarViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Producto>> getLista() {
        if (mListaMutable == null) {
            mListaMutable = new MutableLiveData<>();
        }
        return mListaMutable;
    }

    public LiveData<String> getmMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void cargarLista() {

        if(productos.isEmpty()){
            mMensaje.setValue("No hay productos cargados");
            return;
        }

        productos.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                return o1.getDescripcion().compareTo(o2.getDescripcion());
            }
        });
        mMensaje.setValue("Lista de productos cargada");
        mListaMutable.setValue(productos);
    }
}