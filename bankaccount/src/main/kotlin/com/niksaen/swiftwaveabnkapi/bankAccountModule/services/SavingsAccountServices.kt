package com.niksaen.swiftwaveabnkapi.bankAccountModule.services

class SavingsAccountServices : AccountService(TABLE_NAME) {
    companion object {
        const val TABLE_NAME = "saving_accounts"
    }
}