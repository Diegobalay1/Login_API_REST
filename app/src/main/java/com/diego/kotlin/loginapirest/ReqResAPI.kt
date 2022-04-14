package com.cursosant.android.stores.common.database

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ReqResAPI constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE: ReqResAPI? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this) {
            INSTANCE?: ReqResAPI(context).also {
                INSTANCE = it
            }
        }
    }

    /**
     * RequestQueue es un tipo de objeto especializado en administrar
     * las operaciones de red de forma asincrona además de
     * poder leer o escribir en la caché
     */
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}