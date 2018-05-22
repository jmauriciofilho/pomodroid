package br.unifor.ads.pomodroid.entity

data class Task(
        val id:Long? = null,
        val name:String,
        val description:String,
        val finished:Boolean,
        val estimatedPomodoro:Int,
        val totalPomodoro:Int,
        val taskList:TaskList? = null
)