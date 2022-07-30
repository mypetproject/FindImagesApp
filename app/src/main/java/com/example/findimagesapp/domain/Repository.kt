package com.example.findimagesapp.domain

interface Repository {
    fun downloadDataFirstTime(query: String)
    fun downloadAdditionalData()
}