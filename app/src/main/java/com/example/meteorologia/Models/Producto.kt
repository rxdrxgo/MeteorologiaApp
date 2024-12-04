package com.example.meteorologia.Models

data class Producto(
    var id: String? = null,
    var temperatura: String? = null,  // Este es el valor para la temperatura
    var viento: String? = null,       // Este es el valor para el viento
    var humedad: String? = null,      // Este es el valor para la humedad
    var presion: String? = null       // Este es el valor para la presión
)