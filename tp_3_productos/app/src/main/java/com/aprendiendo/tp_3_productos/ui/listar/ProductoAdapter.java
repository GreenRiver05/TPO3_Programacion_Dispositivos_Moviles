package com.aprendiendo.tp_3_productos.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendiendo.tp_3_productos.R;
import com.aprendiendo.tp_3_productos.modelo.Producto;

import java.util.List;

//1- Armar la clase interna Holder (linea 64)
//2- Armar Adapter con el Holder como parametro y el context
//3- Implementar los metodos del Adapter (todos)
//4- El adapter tiene que recibir una lista, un context y un inflater
//5- Crear el item.xml en la carpeta layout y en el metodo onCreateViewHolder inflarlo
//6- En el metodo onBindViewHolder crear la logica del item

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {

    private List<Producto> listaProductos;
    private Context context;
    private LayoutInflater inflater;

    public ProductoAdapter(List<Producto> listaProductos, Context context, LayoutInflater inflater) {
        this.listaProductos = listaProductos;
        this.context = context;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ProductoAdapter.ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(itemView);
        //retornamos el holder con la vista inflada
    }


    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolderProducto holder, int position) {
        //obtenemos el producto de la lista de productos en la posicion dada
        Producto producto = listaProductos.get(position);
        holder.tvCodigo.setText("Codigo: " + producto.getCodigo());
        holder.tvDescripcion.setText("Descripcion: " + producto.getDescripcion());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
    }


    @Override
    public int getItemCount() {
        return listaProductos.size();
        //retornamos el tamaño de la lista de productos
    }


    //Clase interna HOLDER que represente en java los elementos del item
    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);

        }
    }
}
