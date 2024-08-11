package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun app(modifier: Modifier = Modifier.padding()) {

    var isPlayer1Turn = remember { mutableStateOf(true) }
    var scoreCountPlayer1 = remember { mutableStateOf(0) }
    var scoreCountPlayer2 = remember { mutableStateOf(0) }

    var currentImage = remember { mutableStateOf(1) }
    var images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    if (scoreCountPlayer1.value >= 20 || scoreCountPlayer2.value >= 20) {
        if (scoreCountPlayer1.value > scoreCountPlayer2.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Player 1 Won", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Button(onClick = {
                    scoreCountPlayer1.value = 0
                    scoreCountPlayer2.value = 0
                }) {
                    Text("Reset")
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Player 2 Won",fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Button(onClick = {
                    scoreCountPlayer1.value = 0
                    scoreCountPlayer2.value = 0
                }) {
                    Text("Reset")
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isPlayer1Turn.value)
                Text(text = "Player 1 Turn")
            else
                Text(text = "Player 2 Turn")

            Image(
                painter = painterResource(id = images[currentImage.value - 1]),
                contentDescription = null
            )
            Row {
                Button(
                    onClick = {
                        val random = Random.nextInt(6) + 1
                        scoreCountPlayer1.value += random
                        currentImage.value = random
                        isPlayer1Turn.value = !isPlayer1Turn.value
                    },
                    enabled = if (isPlayer1Turn.value) true else false,
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text("Player 1")
                }

                Button(
                    onClick = {
                        val random = Random.nextInt(6) + 1
                        scoreCountPlayer2.value += random
                        currentImage.value = random
                        isPlayer1Turn.value = !isPlayer1Turn.value
                    },
                    enabled = if (isPlayer1Turn.value) false else true
                ) {
                    Text("Player 2")
                }
            }
        }
    }
}