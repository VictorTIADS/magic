package com.bootcamp.magic.Utils

fun retry(times:Int = 10, timeout:Long = 300,assertion:()->Unit){
    for (i in 0 until times){
        try {
            assertion()
            return
        }catch (e:Throwable){
            if (i == times){
                throw e
            }
            Thread.sleep(timeout)
        }
    }
}