package com.niksaen.swiftwavebankapi.userModule.utils

import java.util.regex.Pattern

class DataValidator {
    companion object{
        private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        private val PHONE_NUMBER_PATTERN = Pattern.compile(
            "[\\+0-9\\(\\)\\- ]{7,19}"
        )
        fun emailVerification(email:String):Boolean{
            return if(email.isNotEmpty()){
                EMAIL_ADDRESS_PATTERN.matcher(email).matches()
            }
            else false
        }

        fun phoneVerification(phone:String):Boolean{
            return if(phone.isNotEmpty()){
                PHONE_NUMBER_PATTERN.matcher(phone).matches()
            }
            else false
        }
    }
}