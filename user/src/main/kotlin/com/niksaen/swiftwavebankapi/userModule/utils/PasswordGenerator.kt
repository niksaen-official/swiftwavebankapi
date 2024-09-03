package com.niksaen.swiftwavebankapi.userModule.utils

class PasswordGenerator {
    companion object{
        fun password():String{
            var password = ""
            val letters = "qwertyuiopasdfghjklzxcvbnm".split("")
            val digits = "1234567890".split("")
            val symbols = "!@#$%^&*(){}:<>?/;'.,".split("")

            val length = (8..16).random()

            for(i in (0..length)){
                val type = (1..3).random()
                password += when(type){
                    1 -> digits.random()
                    2 -> symbols.random()
                    else ->
                        if((1..2).random() == 1) letters.random()
                        else letters.random().uppercase()
                }
            }

            return password
        }
    }
}