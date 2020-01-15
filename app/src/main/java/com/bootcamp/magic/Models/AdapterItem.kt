package com.bootcamp.magic.Models

data class AdapterItem<out T>(val value:T?,val viewType:Int)