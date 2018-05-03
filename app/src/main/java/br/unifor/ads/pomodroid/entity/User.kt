package br.unifor.ads.pomodroid.entity

data class User(
        val id:Int? = null,
        val name:String,
        val email:String,
        val password:String
)