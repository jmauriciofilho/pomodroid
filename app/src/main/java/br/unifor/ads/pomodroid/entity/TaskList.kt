package br.unifor.ads.pomodroid.entity

data class TaskList(
        val id:Int? = null,
        val name:String,
        val description:String,
        val user: User
)