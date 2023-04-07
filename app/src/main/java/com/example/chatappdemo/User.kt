package com.example.chatappdemo

class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    // konstruktor mora biti prazan radi interakcije sa Firebase?

    constructor() {}

    constructor(name: String?, email: String?, uid: String?){
        this.name = name
        this.email = email
        this.uid = uid
    }

}