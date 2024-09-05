package com.niksaen.swiftwaveabnkapi.bankAccountModule.controllers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.AccountCreatingData
import org.springframework.web.bind.annotation.*

@RestController
class CreditAccountController {
    @GetMapping("accounts/credit")
    fun getData(
        @RequestParam("id") id:Long,
        @RequestHeader("user-id") userId:Long
    ){}

    @PostMapping("accounts/credit/create")
    fun create(@RequestBody accountCreatingData: AccountCreatingData) {}

    @PatchMapping("accounts/credit/addPhone")
    fun addPhone(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long,
        @RequestBody phone:String
    ){}

    @PatchMapping("accounts/credit/close")
    fun close(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @PutMapping("account/credit/addCard")
    fun addCard(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @GetMapping("account/credit/getCards")
    fun getCards(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}
}