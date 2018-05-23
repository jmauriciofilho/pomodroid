package br.unifor.ads.pomodroid.entity

data class Task(
        val id:Long? = null,
        val name:String,
        val description:String,
        val finished:Boolean? = null,
        val estimatedPomodoro:Int? = null,
        val totalPomodoro:Int? = null,
        val taskList:TaskList? = null
)