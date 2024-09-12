package com.example.jogodedados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    var dice1 by remember { mutableIntStateOf(1) }
    var dice2 by remember { mutableIntStateOf(1) }
    var totalGames by remember { mutableIntStateOf(0) }
    var totalWins by remember { mutableIntStateOf(0) }
    var result by remember { mutableStateOf("") }

    fun roll() {
        dice1 = (1..6).random()
        dice2 = (1..6).random()
        totalGames++

        if(dice1 + dice2 in setOf(7, 11)) {
            totalWins++
            result = "Ganhou"
        } else {
            result = "Perdeu"
        }
    }

    val winPercentage = if (totalGames > 0) (totalWins * 100) / totalGames else 0

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JogoDeDadosTheme {
        Dice()
    }
}