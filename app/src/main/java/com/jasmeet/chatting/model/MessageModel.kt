package com.jasmeet.chatting.model

data class MessageModel(
    var Message : String ?= null,
    var senderId : String ?= null,
    var timeStamp : Long ?=0

)
