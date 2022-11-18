package com.example.nakanokugarbage.Model

data class Block(var index: Int, var name: String){
    fun getJpName(): String {
        return name + "丁目"
    }
}
