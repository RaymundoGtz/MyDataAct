package com.iest.misdatos

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iest.misdatos.ui.theme.MisDatosTheme
import com.iest.misdatos.viewmodels.PreferencesViweModel
import kotlinx.coroutines.launch
import java.time.format.TextStyle

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

        var weight by rememberSaveable {
            mutableStateOf("")

        }





        var corrutinasScope = rememberCoroutineScope()
        var savedAge = preferencesViewModel.age.collectAsState(initial = 0)
        var savedName = preferencesViewModel.name.collectAsState(initial = "???")
        var savedHobby = preferencesViewModel.hobby.collectAsState(initial = "???")
        var savedHeight = preferencesViewModel.height.collectAsState(initial = 0)
        var savedWeight= preferencesViewModel.weight.collectAsState(initial = 0)

        val saved = if (savedName.value == "???") {
            false
        } else {
            true
        }



        Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

            if(saved){

                Text(modifier = Modifier.padding(start = 25.dp, top = 20.dp, end = 25.dp), textAlign = TextAlign.Center, fontSize = 20.sp,text = "Tu nombre es ${savedName.value}, tienes ${savedAge.value}, tu hobby es ${savedHobby.value}, mides ${savedHeight.value} cm y pesas ${savedWeight.value}kg")


            }else{

                Text(modifier = Modifier
                    .padding(bottom = 20.dp), fontSize = 20.sp, text = "Introduce tus datos")

                TextField(label = {Text(text = "Nombre")}, value = name, onValueChange = {
                    name = it
                })

                TextField(label = { Text(text = "Edad")}, value = age, onValueChange = {
                    age = it
                })

                TextField(label = { Text(text = "Hobby")}, value = hobby, onValueChange = {
                    hobby = it
                })

                TextField(label = { Text(text = "Altura")}, value = height, onValueChange = {
                    height = it
                })

                TextField(label = { Text(text = "Peso")}, value = weight, onValueChange = {
                    weight = it
                })

                Button(modifier = Modifier.padding(top = 20.dp), onClick = {
                    corrutinasScope.launch {

                        if(age.toIntOrNull() != null){
                            preferencesViewModel.setNameAndAgeAndHobby(name, age.toInt(), hobby, height.toInt(), weight.toInt())
                        }

                    }
                }) {
                    Text(text = "Guardar Datos")

                }

            }
            
        }



    }
}


