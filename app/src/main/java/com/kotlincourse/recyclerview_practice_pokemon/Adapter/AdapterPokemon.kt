package com.kotlincourse.recyclerview_practice_pokemon.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.recyclerview_practice_pokemon.Models.Pokemon
import com.kotlincourse.recyclerview_practice_pokemon.R
import com.squareup.picasso.Picasso

class AdapterPokemon (private val pokemonlist: ArrayList<Pokemon>) : RecyclerView.Adapter<AdapterPokemon.ViewHolder>() {// 1- this class has to extend from RecyclerView

    //-2 create the ViewHolder Calss. In this class we need to create variables when we going to save the components from the layout (txtview, imageview, etc)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvname: TextView = itemView.findViewById(R.id.tvName)
        val tvnumber: TextView = itemView.findViewById(R.id.tvPokeNumber)
        val imgurl: ImageView = itemView.findViewById(R.id.imageView1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Create a variable, inflate the layout where we-ve the cardview
        // here we are inflating the variable X, we going to inflate from parent context (in this case is the AdapterPokecom class) to the layout that we want to inflate, then add false.....
        var x = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(x)
    }

    override fun getItemCount(): Int = pokemonlist.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //take the variables that we create in the ViewHolder and we going to assign them the values from the list
        holder.tvname.text = pokemonlist[position].name
        holder.tvnumber.text = pokemonlist[position].nationalPokedexNumber.toString()
        Picasso.get().load(pokemonlist[position].imageUrl).into(holder.imgurl)




    }
}