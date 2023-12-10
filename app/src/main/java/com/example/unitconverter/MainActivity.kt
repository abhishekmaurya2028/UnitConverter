package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Abhishek")
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){

        var inputValue by remember{ mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Meters") }
        var outputUnit by remember { mutableStateOf("Meters") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpended by remember { mutableStateOf(false) }
        val conversionFactor = remember {
            mutableStateOf(1.0) //unit in meter
        }
        val oConversionFactor = remember {
            mutableStateOf(1.0) //unit in meter
        }

        //--------custom text syile


        // you can create another function inside a composable function
        fun convertUnit(){
            //convert the number in double if number is not null
            //this will get input from user i.e. getText function
            // ?: elvis operator  short if else stmt

            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value ).roundToInt() / 100
            outputValue = result.toString()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text ="Unit Converter" ,
                style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(16.dp))
            //dp -> density pixel

            OutlinedTextField(value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnit()
                    //here goes what should happen , when
                    // the value of OutLineTextField changes
                },
                label ={ Text(text = "Enter Value")})
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                //input box
                Box {

                    // input button
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit )
//
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeters") },
                            onClick = {
                                iExpanded = false;
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                convertUnit()

                            })
                        DropdownMenuItem(
                            text = { Text(text = "Meters") },
                            onClick = {
                                iExpanded = false;
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Feet") },
                            onClick = {
                                iExpanded = false;
                                inputUnit = "Feat"
                                conversionFactor.value = 0.3048
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Millimeters") },
                            onClick = {
                                iExpanded = false;
                                inputUnit = "Millimeters"
                                conversionFactor.value = 0.001
                                convertUnit()
                            })
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                //output box
                Box {
                    Button(onClick = {
                        oExpended = true
                    }) {
                        Text(text = outputUnit )
//
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeters") },
                            onClick = {
                                oExpended = false;
                                outputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Meters") },
                            onClick = {
                                oExpended = false;
                                outputUnit = "Meters"
                                oConversionFactor.value = 1.0
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Feet") },
                            onClick = {
                                oExpended = false;
                                outputUnit = "Feat"
                                oConversionFactor.value = 0.3048
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Millimeters") },
                            onClick = {
                                oExpended = false;
                                outputUnit = "Millimeters"
                                oConversionFactor.value = 0.001
                                convertUnit()
                            })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result: $inputValue $outputValue" ,
                style = MaterialTheme.typography.displayMedium
            )
        }

}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}