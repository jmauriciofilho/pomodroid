package br.unifor.ads.pomodroid.util

inline fun <N : Number> N.notEquals(value: N): Boolean {
    return !this.equals(value)
}