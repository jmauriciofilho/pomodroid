package br.unifor.ads.pomodroid.entity

data class User(
        val id:Long? = null,
        val name:String,
        val email:String,
        val password:String
)