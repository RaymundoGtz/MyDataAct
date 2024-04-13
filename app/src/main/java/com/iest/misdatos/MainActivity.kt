package com.iest.misdatos

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.iest.misdatos.ui.theme.MisDatosTheme
import com.iest.misdatos.viewmodels.PreferencesViweModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context:Context = LocalContext.current
            val preferencesViweModel = PreferencesViweModel(context)
            MisDatosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    SettingsView(preferencesViweModel)

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SettingsView(preferencesViewModel: PreferencesViweModel){
    Column (Modifier.fillMaxSize()) {

        var name by rememberSaveable{
            mutableStateOf("0")
        }

        var age by rememberSaveable {
            mutableStateOf("")

        }

        var hobby by rememberSaveable {
            mutableStateOf("")

        }

        var height by rememberSaveable {
            mutableStateOf("")

        }


        var saved = true
        var corrutinasScope = rememberCoroutineScope()
        var savedAge = preferencesViewModel.age.collectAsState(initial = 0)
        var savedName = preferencesViewModel.name.collectAsState(initial = "???")
        var savedHobby = preferencesViewModel.hobby.collectAsState(initial = "???")
        var savedHeight = preferencesViewModel.height.collectAsState(initial = 0)

        Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){

            if(saved){

                Text(text = "Tu nombre es ${savedName.value}, tienes ${savedAge.value}, tu hobby es ${savedHobby.value}  y tu altura es ${savedHeight.value} cm")


            }else{

                TextField(label = {Text(text = "MaferSex")}, value = name, onValueChange = {
                    name = it
                })

                TextField(value = age, onValueChange = {
                    age = it
                })

                TextField(value = hobby, onValueChange = {
                    hobby = it
                })

                TextField(value = height, onValueChange = {
                    height = it
                })
                Button(onClick = {
                    corrutinasScope.launch {

                        if(age.toIntOrNull() != null){
                            preferencesViewModel.setNameAndAgeAndHobby(name, age.toInt(), hobby, height.toInt())
                        }

                    }
                }) {
                    Text(text = "Guardar Datos")

                }

            }

            
                Text(text = "Tu nombre es ${savedName.value}, tienes ${savedAge.value}, tu hobby es ${savedHobby.value}  y tu altura es ${savedHeight.value} cm")

        }



    }
}

