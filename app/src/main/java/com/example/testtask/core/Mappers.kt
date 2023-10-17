package com.example.testtask.core

// one direction
interface Mapper<I, O> {
    fun map(input: I): O
}