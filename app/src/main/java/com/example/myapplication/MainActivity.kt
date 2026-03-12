package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinhaTela()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Composable
fun MinhaTela(){

    val PalmeirasGreen = Color(0xFF006437)
    var n1 by remember { mutableStateOf("") }
    var n2 by remember { mutableStateOf("") }
    var result by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier
        .padding(5.dp, 35.dp, 5.dp, 5.dp)
        .fillMaxSize()
        .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {



        Column(
            modifier = Modifier
                .fillMaxSize()
            , verticalArrangement = Arrangement.Center,
           horizontalAlignment =  Alignment.CenterHorizontally
            ) {

            Image(
                painter = painterResource(id = R.drawable.logo_palmeiras),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(0.dp, 0.dp, 0.dp, 100.dp)
            )


            val focusInputOneRequester = remember { FocusRequester() }

            val focusInputTwoRequester = remember { FocusRequester() }


            TextField(
                value = n1,
                onValueChange = { n1 = it } ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(
                    "Digite o primeiro número: "
                )},
                modifier = Modifier
                    .background(Color.White)
                    .focusRequester(focusInputOneRequester)

            )
            TextField(
                value = n2,
                onValueChange = { n2 = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.DarkGray
                ),
                label = { Text(
                    "Digite o segundo número: "
                )},
                modifier = Modifier
                    .background(Color.White)
                    .focusRequester(focusInputTwoRequester)

            )
            Text("Soma: ${result}",
                modifier = Modifier
                    .padding(5.dp, 35.dp, 5.dp, 5.dp)
                    ,
                fontSize = 35.sp
            )


            val enabled = n1.isNotBlank() && n2.isNotBlank()


            FilledTonalButton(
                onClick = {
                    val n1_input = n1.toIntOrNull() ?: 0
                    val n2_input = n2.toIntOrNull() ?: 0

                    if (n1_input == null || n2_input == null) {
                    }
                    result = somar(n1_input, n2_input)
                    n1 = ""
                    n2 = ""

                    focusInputTwoRequester.requestFocus()

                },
                enabled = enabled,
                modifier = Modifier
                    .border(
                        width = 3.dp, color = Color.Cyan, shape = RoundedCornerShape(50.dp)
                    )

                    .background(
                        color = if (enabled) Color.Green else Color.LightGray,
                        shape = RoundedCornerShape(50.dp)
                    ),
                colors = ButtonColors(
                    containerColor = PalmeirasGreen,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.Gray,
                ),


                ) {
                Text(
                    "Clique Aqui",
                    modifier = Modifier,
                    color = Color.White

                )
            }
        }

    }
}

fun somar ( n1 : Int = 0 , n2 : Int = 0) : Int{
    return n1 + n2;
}

//Criar um novo composable com:
//n1 e n2
//Receber esses valores atraves de 2 textField
//e mostrar a soma deles
@Preview(showBackground = true)
@Composable
fun MinhaTelaPreviw(){
    MinhaTela()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}