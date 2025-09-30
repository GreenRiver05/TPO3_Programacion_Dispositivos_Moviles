package com.aprendiendo.tp_3_productos.ui.cargar;

import static com.aprendiendo.tp_3_productos.MainActivity.*;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.aprendiendo.tp_3_productos.modelo.Producto;


//1- Crear el ViewModel
//2- Crear los MutableLiveData
//3- Crear los metodos getmError() y getmMExito() para instanciar los MutableLiveData
//4- Crear el guardarProducto() para guardar el producto
//5- Setear los Mutables con los mensaje de error o de exito
public class CargarViewModel extends ViewModel {

    private  MutableLiveData<String> mError;
    private MutableLiveData<String> mExito;

    public CargarViewModel() {

    }

    public LiveData<String> getmError() {
        if (mError==null) {
            mError=new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getmExito() {
        if (mExito==null) {
            mExito=new MutableLiveData<>();
        }
        return mExito;
    }

    public void guardarProducto(String codigo, String descripcion, String precio) {

        //Valido que los campos no esten vacios
        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            mError.setValue("Todos los campos son obligatorios");
            return;
        }

        //Valido que el codigo no este repetido
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)){
                mError.setValue("El codigo ya existe");
                return;
            }
        }

        //Creo el producto y lo agrego a la lista de productos
        Producto producto = new Producto(codigo, descripcion, Float.parseFloat(precio));
        productos.add(producto);
        mExito.setValue("Producto " + codigo + " cargado correctamente");
    }
}