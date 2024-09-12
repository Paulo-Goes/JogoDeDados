package com.example.jogodedados

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jogodedados.ui.theme.JogoDeDadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JogoDeDadosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Dice()
                }
            }
        }
    }
}

@Composable
fun Dice() {
    var dice1 by remember { mutableIntStateOf(0) }
    var dice2 by remember { mutableIntStateOf(0) }
    var totalGames by remember { mutableIntStateOf(0) }
    var totalWins by remember { mutableIntStateOf(0) }
    var result by remember { mutableStateOf("Unkown") }

    fun roll() {
        dice1 = (1..6).random()
        dice2 = (1..6).random()
        totalGames++

        if (dice1 + dice2 in setOf(7, 11)) {
            totalWins++
            result = "You won!"
        } else {
            result = "You lost."
        }
    }

    val winPercentage = if (totalGames > 0) (totalWins * 100) / totalGames else 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            PrintDice(dice1)
            PrintDice(dice2)
        }

        Button(onClick = { roll() }, modifier = Modifier.padding(vertical = 16.dp)) {
            Text("Play")
        }

        Text("Result: $result", modifier = Modifier.padding(vertical = 16.dp))
        Text("Session Score: $totalWins/$totalGames = $winPercentage%")
    }
}

@Composable
fun PrintDice(value: Int) {
    Text(
        text = value.toString(),
        fontSize = 100.sp,
        modifier = Modifier
            .border(2.dp, Color.Black)
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JogoDeDadosTheme {
        Dice()
    }
}