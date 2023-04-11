package com.example.emailauth

class Question {
    var question: String? = null
    var op1: String? = null
    var op2: String? = null
    var op3: String? = null
    var op4: String? = null
    var ans: String? = null

    constructor(
        question: String?,
        op1: String?,
        op2: String?,
        op3: String?,
        op4: String?,
        ans: String?
    ) {
        this.question = question
        this.op1 = op1
        this.op2 = op2
        this.op3 = op3
        this.op4 = op4
        this.ans = ans
    }

    constructor()

}