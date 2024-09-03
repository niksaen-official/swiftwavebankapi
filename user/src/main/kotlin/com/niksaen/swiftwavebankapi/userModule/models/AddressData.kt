package com.niksaen.swiftwavebankapi.userModule.models

import java.io.Serializable

data class AddressData(
    val country:String,
    val region:String,
    val district:String,
    val settlement:String,
    val street:String,
    val house:String,
    val apartment:Int?
) : Serializable