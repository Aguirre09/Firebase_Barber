package com.example.barberi.UserFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barberi.BasesDatos.Productos
import com.example.barberi.R
import kotlinx.android.synthetic.main.item_productos.view.*







class ProductosRVAdapter(
    private val context: Context,
    private val  productosList: ArrayList<Productos>
) : RecyclerView.Adapter<ProductosRVAdapter.ProductosViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductosViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_productos, parent, false)
        return ProductosViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = productosList.size




    override fun onBindViewHolder(
        holder: ProductosViewHolder,
        position: Int
    ) {
        val productos:Productos= productosList[position]
        holder.bindProducto(productos)
    }

    class ProductosViewHolder (
        itemView: View,
        context: Context
    ):RecyclerView.ViewHolder(itemView) {

        fun bindProducto(productos: Productos){

            // muestra la descripcion del producto
            itemView.tv_nombre.text = productos.descrip
            itemView.tv_precio.text = "$"+productos.pesos.toString()
            itemView.Cantidad.text = productos.cantidad.toString()
        }

    }



}












