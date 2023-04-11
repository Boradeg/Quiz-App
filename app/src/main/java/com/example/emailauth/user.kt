package com.example.emailauth

class user {
    var name: String? = null
    var email: String? = null
    var pass: String? = null

    constructor()
    constructor(name: String?, email: String?, pass: String?) {
        this.name = name
        this.email = email
        this.pass = pass
    }


}