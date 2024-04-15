package com.iest.misdatos.viewmodels

import android.content.Context
import androidx.compose.ui.text.font.FontWeight
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PreferencesViweModel(val context: Context) {

    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val NAME = stringPreferencesKey( "name")
        val AGE = intPreferencesKey("age")
        val HOBBY = stringPreferencesKey("hobby")
        val HEIGHT = intPreferencesKey("height")
        val WEIGHT = intPreferencesKey("weight")
    }
    val age: Flow<Int> = context.dataStore.data.map {
            preferences ->
        // No type safety.
        preferences[AGE] ?:0
    }

    val name: Flow<String> = context.dataStore.data.map {
        preferencies ->
        preferencies[NAME] ?:""
    }

    val hobby: Flow<String> = context.dataStore.data.map {
            preferencies ->
        preferencies[HOBBY] ?:""
    }

    val height: Flow<Int> = context.dataStore.data.map {
            preferencies ->
        preferencies[HEIGHT] ?: 0
    }

    val weight: Flow<Int> = context.dataStore.data.map {
            preferencies ->
        preferencies[WEIGHT] ?: 0
    }

    //EQUIVALENTE A SETNAME Y SETAGE
    suspend fun setNameAndAgeAndHobby(name:String, age:Int, hobby:String, height:Int, weight:Int){
        context.dataStore.edit { settings ->
            settings[NAME] = name
            settings[AGE] = age
            settings[HOBBY] = hobby
            settings[HEIGHT] = height
            settings[WEIGHT] = weight
        }
    }
}
