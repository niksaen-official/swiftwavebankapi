package com.niksaen.swiftwaveabnkapi.bankAccountModule.controllers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.AccountCreatingData
import org.springframework.web.bind.annotation.*

@RestController
class PaymentAccountController{

    @GetMapping("accounts/payment")
    fun getData(
        @RequestParam("id") id:Long,
        @RequestHeader("user-id") userId:Long
    ){}

    @PostMapping("accounts/payment/create")
    fun create(@RequestBody accountCreatingData: AccountCreatingData) {}

    @PatchMapping("accounts/payment/addPhone")
    fun addPhone(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long,
        @RequestBody phone:String
    ){}

    @PatchMapping("accounts/payment/close")
    fun close(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @PutMapping("account/payment/addCard")
    fun addCard(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @GetMapping("account/payment/getCards")
    fun getCards(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}
}