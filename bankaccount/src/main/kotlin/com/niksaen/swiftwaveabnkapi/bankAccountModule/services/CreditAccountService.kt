package com.niksaen.swiftwaveabnkapi.bankAccountModule.services

class CreditAccountService : AccountService(TABLE_NAME) {
    companion object {
        const val TABLE_NAME = "credit_accounts"
    }
}