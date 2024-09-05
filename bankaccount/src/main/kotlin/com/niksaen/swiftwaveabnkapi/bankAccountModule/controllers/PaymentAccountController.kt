package com.niksaen.swiftwaveabnkapi.bankAccountModule.controllers

import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.AccountCreatingData
import com.niksaen.swiftwaveabnkapi.bankAccountModule.models.PaymentAccountCreatingData
import com.niksaen.swiftwaveabnkapi.bankAccountModule.paymentAccountService
import com.niksaen.swiftwavebankapi.core.ServerResponse
import org.springframework.web.bind.annotation.*

@RestController
class PaymentAccountController{

    @GetMapping("accounts/payment/all")
    fun getAll(
        @RequestHeader("user-id") userId:Long
    ):ServerResponse{
        return if(userId > 0)
            paymentAccountService.getAll(userId)
        else
            ServerResponse(false,"Unauthorized request")
    }

    @GetMapping("accounts/payment")
    fun getData(
        @RequestParam("id") id:Long,
        @RequestHeader("user-id") userId:Long
    ):ServerResponse{
        return if(userId > 0)
            if(id > 0)
                paymentAccountService.getData(id,userId)
            else
                ServerResponse(false,"Account id is empty")
        else
            ServerResponse(false,"Unauthorized request")
    }

    @PostMapping("accounts/payment/create")
    fun create(@RequestBody accountCreatingData: PaymentAccountCreatingData) : ServerResponse {
        return if(accountCreatingData.userId > 0)
            paymentAccountService.create(accountCreatingData)
        else
            ServerResponse(false,"Unauthorized request")
    }

    @PatchMapping("accounts/payment/addPhone")
    fun addPhone(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long,
        @RequestBody phone:String
    ):ServerResponse{
        return if(userId > 0)
            if(id > 0)
                paymentAccountService.addPhone(id, userId, phone)
            else
                ServerResponse(false,"Account id is empty")
        else
            ServerResponse(false,"Unauthorized request")
    }

    @PatchMapping("accounts/payment/close")
    fun close(
        @RequestParam("id") id: Long,
        @RequestHeader("user-id") userId: Long
    ):ServerResponse{
        return if(userId > 0)
            if(id > 0)
                paymentAccountService.close(id,userId)
            else
                ServerResponse(false,"Account id is empty")
        else
            ServerResponse(false,"Unauthorized request")
    }

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