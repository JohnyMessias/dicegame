package co.jmessias.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.jmessias.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun DrawScope.drawPip(offsetX: Float, offsetY: Float) {
    drawCircle(
        color = Color.Black,
        radius = 20f,
        center = Offset(offsetX, offsetY)
    )
}

fun DrawScope.bullet(number: Int) {
    val third = size.width / 3
    val center = size.width / 2

    when (number) {
        1 -> drawPip(center, center)
        2 -> {
            drawPip(third, size.height - third)
            drawPip(size.width - third, third)
        }

        3 -> {
            drawPip(third, third)
            drawPip(center, center)
            drawPip(size.width - third, size.height - third)
        }

        4 -> {
            drawPip(third, third)
            drawPip(size.width - third, third)
            drawPip(third, size.height - third)
            drawPip(size.width - third, size.height - third)
        }

        5 -> {
            drawPip(third, third)
            drawPip(size.width - third, third)
            drawPip(center, center)
            drawPip(third, size.height - third)
            drawPip(size.width - third, size.height - third)
        }

        6 -> {
            drawPip(third, third)
            drawPip(center, third)
            drawPip(size.width - third, third)
            drawPip(third, size.height - third)
            drawPip(center, size.height - third)
            drawPip(size.width - third, size.height - third)
        }
    }
}

@Composable
fun Dice(number: Int, modifier: Modifier) {
    Canvas(
        modifier = modifier
            .size(100.dp)
    ) {
        drawRoundRect(
            color = Color.Green,
            size = size,
            cornerRadius = CornerRadius(16f, 16f)
        )
        bullet(number = number)
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val diceValue = remember { mutableStateOf(1) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Dice(
            diceValue.value, modifier = Modifier
                .align(Alignment.Center)
        )

        Button(
            onClick = {
                diceValue.value = (1..6).random()
            }, modifier = Modifier
                .align(Alignment.Center)
                .offset(
                    y = 100.dp
                )
        ) {
            Text("Jogar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
        App()
    }
}