package com.example.dh3teste.api

open class ResponseAPI {
    class Success(val data: Any?): ResponseAPI()
    class Error (val message: String): ResponseAPI()
}