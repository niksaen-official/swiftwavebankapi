package com.niksaen.swiftwaveabnkapi.bankAccountModule.controllers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.AccountCreatingData
import org.springframework.web.bind.annotation.*

@RestController
class SavingsAccountsController {
    @GetMapping("accounts/savings")
    fun getData(
        @RequestParam("id") id:Long,
        @RequestHeader("user-id") userId:Long
    ){}

    @PostMapping("accounts/savings/create")
    fun create(@RequestBody accountCreatingData: AccountCreatingData) {}

    @PatchMapping("accounts/savings/addPhone")
    fun addPhone(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long,
        @RequestBody phone:String
    ){}

    @PatchMapping("accounts/savings/close")
    fun close(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @PutMapping("account/savings/addCard")
    fun addCard(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}

    @GetMapping("account/savings/getCards")
    fun getCards(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ){}
}