package com.example.numberguessinggame2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame2.ui.theme.NumberGuessingGame2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGame2Theme {
                TextAndButton()
            }
        }
    }
}
var random: Int = Random.nextInt(1, 1000)

@Composable
fun TextAndButton(){

    var textnumber by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("Please enter your guess :") }
    var points by remember {mutableStateOf(0)}

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.LightGray),




            ){
        Text(
            text = "Number Guessing Game",
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            CircleImageView()
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "$hint",
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 20.dp)

            )
        Row(
            horizontalArrangement = Arrangement.Center
            ) {
            TextField(
                value = textnumber,
                onValueChange = {
                    textnumber = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.hint))
                },
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 20.dp)
                )
            

            Button(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(10.dp),
                onClick = {
                    val number: Int = textnumber.toString().toInt()

                    if (number < random){
                        points ++
                        hint = "Wrong, your number is lower"

                    } else if (number > random) {
                        points ++
                        hint ="Wrong, your number is higher"

                    } else{

                        hint ="YOU WIN!!, your number is right"

                    }
                    textnumber = ""

                }

            ) {
                Text(text = stringResource(id = R.string.check))
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
                ){

            Text(
                text = "Guessing Number : $points",
                textAlign = TextAlign.Center,

            )
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White),

                onClick = {
                    random = Random.nextInt(1, 1000)
                    hint = "Please enter your guess:"
                    points = 0

                }
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }
    } 

}
@Composable
fun CircleImageView() {
    Image(
        painter = painterResource(R.drawable.question),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(size = 50.dp)) // clip to the circle shape
            .border(1.dp, Color.Gray, RoundedCornerShape(size = 50.dp))//optional
            .padding(30.dp)
    )
}