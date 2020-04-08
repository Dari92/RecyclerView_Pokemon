package com.kotlincourse.recyclerview_practice_pokemon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.kotlincourse.recyclerview_practice_pokemon.Adapter.AdapterPokemon
import com.kotlincourse.recyclerview_practice_pokemon.Models.PokemonContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    //1- Create variables for the service
    private lateinit var gson: Gson
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var request: Request

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //2- Initialize the variables
        gson = Gson()
        okHttpClient = OkHttpClient()

        //set the layoutManager
       rv1.layoutManager = GridLayoutManager(this, 2)
        //rv1.layoutManager = LinearLayoutManager(this)

        //Run the function getPokemon
        runBlocking { getPokemon() }


    }

  //3- create suspend function, suspend bc it-s a corroutine
    private suspend fun getPokemon(){
        request = Request.Builder().url("https://api.pokemontcg.io/v1/cards?subtype=Restored").build() // 4- here we-re adding the url from the API

        //Start the corroutine
        withContext(Dispatchers.IO){// IO bc we-re using networking
            try {

                var response = okHttpClient.newCall(request).execute() // in this variable we-re doing the Call to the API with OKHHTPClient variable
                var result = response.body()?.string() //with this line we-re getting all the elements from the previous request
                var pokemonJson = gson.fromJson(result, PokemonContainer::class.java) //Pase the Json to the class model.

                val rvAdapter = AdapterPokemon(pokemonJson.cards) //getting the list and assign it to a variable.
                rv1.adapter = rvAdapter // assign the previous variable to the recycler.adapter

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}
