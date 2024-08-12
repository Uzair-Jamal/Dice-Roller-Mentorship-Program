package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun app(modifier: Modifier = Modifier, innerPadding: PaddingValues = PaddingValues()) {

    var isPlayer1Turn = remember { mutableStateOf(true) }
    var scoreCountPlayer1 = remember { mutableStateOf(0) }
    var scoreCountPlayer2 = remember { mutableStateOf(0) }

    var currentImage = remember { mutableStateOf(0) }
    var images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    val buttonColor =
        if (scoreCountPlayer1.value >= 20) ButtonDefaults.buttonColors(Color.Green) else ButtonDefaults.buttonColors(
            Color.Blue
        )
    if (scoreCountPlayer1.value >= 20 || scoreCountPlayer2.value >= 20) {
        if (scoreCountPlayer1.value > scoreCountPlayer2.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dice Roller Game",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "Player 1 Won",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Button(
                    onClick = {
                        scoreCountPlayer1.value = 0
                        scoreCountPlayer2.value = 0
                        currentImage.value = 0

                    },
                    colors = buttonColor
                ) {
                    Text("Restart", color = Color.White)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dice Roller Game",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Blue
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Player 2 Won",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Button(
                    onClick = {
                        scoreCountPlayer1.value = 0
                        scoreCountPlayer2.value = 0
                        currentImage.value = 0
                    },
                    colors = buttonColor
                ) {
                    Text("Restart", color = Color.White)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dice Roller Game",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .border(2.dp, Color.LightGray),
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = "Player 1 Score: ${scoreCountPlayer1.value}",
                    color = Color.Green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Player 2 Score: ${scoreCountPlayer2.value}",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .border(2.dp, Color.LightGray)
                    .fillMaxHeight(.5f)
                    .fillMaxWidth(.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Image(
                    painter = if (currentImage.value == 0) {
                        painterResource(R.drawable.start_game)
                    } else painterResource(id = images[currentImage.value - 1]),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val random = Random.nextInt(6) + 1
                            scoreCountPlayer1.value += random
                            currentImage.value = random
                            isPlayer1Turn.value = !isPlayer1Turn.value
                        },
                        enabled = if (isPlayer1Turn.value) true else false,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                        modifier = Modifier.fillMaxWidth(.3f)
                    ) {
                        Text("Player 1", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = {
                            val random = Random.nextInt(6) + 1
                            scoreCountPlayer2.value += random
                            currentImage.value = random
                            isPlayer1Turn.value = !isPlayer1Turn.value

                        },
                        enabled = if (isPlayer1Turn.value) false else true,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth(.5f)
                    ) {
                        Text("Player 2", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (isPlayer1Turn.value)
                    Text(text = "Player 1 Turn", color = Color.Blue, fontSize = 18.sp)
                else
                    Text(text = "Player 2 Turn", color = Color.Blue, fontSize = 18.sp)
            }
        }
    }
}