package com.example.projectx.utlities

import dagger.Reusable
import javax.inject.Inject

@Reusable
class TestUtils @Inject constructor() {

    fun testFun():String{
        return "Hello world"
    }
}