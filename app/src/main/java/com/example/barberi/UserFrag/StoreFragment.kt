package com.example.barberi.UserFrag

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barberi.BasesDatos.Productos
import com.example.barberi.BasesDatos.ProductosDAO

import androidx.room.*
import com.example.barberi.R
import com.example.barberi.SesionRoom
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Tag
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.item_productos.*


/**


 */


class StoreFragment : Fragment() {

    val allProductos : MutableList<Productos> = mutableListOf()
    lateinit var productosRVAdapter: ProductosRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_store, container, false)


        productosRVAdapter = ProductosRVAdapter(
            activity!!.applicationContext,
            allProductos as ArrayList<Productos>
        )
           root.rv_productos.layoutManager = LinearLayoutManager(
             activity!!.applicationContext,
            RecyclerView.VERTICAL,
          false
       )
        root.rv_productos.setHasFixedSize(true)


        //var productosDAO:ProductosDAO = SesionRoom.database.productosDao()
        //var allProductos: List<Productos> = productosDAO.getProductos()
        //var productosRVAdapter = ProductosRVAdapter(
          //  activity!!.applicationContext,
            //allProductos as ArrayList<Productos>
        //)

        root.rv_productos.adapter = productosRVAdapter


        return root


    }

    override fun onResume(){
        super.onResume()
        cargarProductos()
    }

    private fun cargarProductos() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Productos")
        allProductos.clear()



        val postListener=  object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
             for (snapshot in dataSnapshot.children) {

                   System.out.println(dataSnapshot.toString())

                   val productos= snapshot.getValue(Productos::class.java)
                    allProductos.add(productos!!)
                }
                productosRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Lista", "failed! ", error.toException())
            }

        }
        myRef.addValueEventListener(postListener)

    }







}