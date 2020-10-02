package com.example.projectx.utlities

import android.content.SharedPreferences
import com.example.projectx.dagger.GeneralSharedPref
import com.example.projectx.dagger.UserSharedPref
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Sample class showing how classes can be injected
 */
@Singleton
class TestRunner @Inject constructor (private val testUtils: TestUtils,
                                      @GeneralSharedPref private val generalSharedPreferences: SharedPreferences,
                                      @UserSharedPref private val userSharedPref: SharedPreferences) {


}