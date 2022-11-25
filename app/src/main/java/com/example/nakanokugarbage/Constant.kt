package com.example.nakanokugarbage

class Constant {
    companion object {
        const val CITY_BLOCK_TABLE = "cityblock"
        const val FIREBASE_DB_BLOCK = "block_jp"
    }
    enum class SheardPreferenceName(name: String){
        UserInfo("UserInfo");
        enum class UserInfoKey(name: String){
            BlockNo("block_no"),
            BlockName("blcok_name");
        }
    }
}