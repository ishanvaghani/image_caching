package com.example.imageloading

object ComageModule {

    lateinit var comageModuleBridge: ComageModuleBridge

    fun init(comageModuleBridge: ComageModuleBridge) {
        this.comageModuleBridge = comageModuleBridge
    }
}