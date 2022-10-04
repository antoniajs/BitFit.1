package com.example.bitfit1

import android.app.Application

class BitFitApplication : Application(){
    val db by lazy {AppDatabase.getInstance(this)}
}