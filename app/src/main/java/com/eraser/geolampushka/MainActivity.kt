package com.eraser.geolampushka

import android.graphics.fonts.FontFamily
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eraser.geolampushka.ui.theme.GeoLampushkaTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoLampushkaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF76B3BE)
                ) {
                    MainScreen()
                }
            }
        }
    }
}

fun Modifier.advancedShadow(
    elevation: Dp,
    shape: Shape = RectangleShape,
    shadowColor: Color = Color.Black
) = this.then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawCircle(
                    size.width / 2,
                    size.height / 2,
                    size.width / 2,
                    Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        this.color = shadowColor.toArgb()
                        setShadowLayer(
                            elevation.toPx() * 2.5f, // Увеличиваем радиус для рассеивания тени
                            elevation.toPx() * 0.2f, // небольшое смещение по X (вправо)
                            elevation.toPx() * 0.8f, // большее смещение по Y (вниз)
                            shadowColor.toArgb()
                        )
                    }
                )
                drawContent()
            }
        }
    }
)



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF93D4E1)),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.padding(top = 31.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 31.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Кнопка-иконка яркости слева
                BrightnessIcon(modifier = Modifier.padding(start = 34.dp))

                // Надпись GeoLampushka
                GeoLampushkaLabel(modifier = Modifier.padding(bottom = 4.dp)) // 4.dp для "феншуя"

                // Кнопка-иконка настроек справа
                SettingsIcon(modifier = Modifier.padding(end = 34.dp))
            }
            Spacer(modifier = Modifier.height(114.dp - 31.dp - 50.dp)) // Вычитаем отступ сверху и высоту иконок

            // Ваш прямоугольник
            RectangleWithText()
        }
    }
}
@Composable
fun RectangleWithText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 34.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 4.dp)  // Уменьшаем размер тени
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(26.dp)
                )
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(26.dp))
        )

        Image(
            painter = painterResource(id = R.drawable.bulp_name_container),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(26.dp)),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "GeoLampushka",
                fontSize = 30.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}




@Composable
fun BrightnessIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.brightness),
        contentDescription = "Brightness Icon",
        modifier = modifier.size(50.dp)
            .shadow(elevation = 8.dp, shape = CircleShape)
    )
}

@Composable
fun SettingsIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.settings),
        contentDescription = "Settings Icon",
        modifier = modifier.size(50.dp)
            .shadow(elevation = 8.dp, shape = CircleShape)
    )
}

@Composable
fun GeoLampushkaLabel(modifier: Modifier = Modifier) {
    Text(
        text = "GEOLAMPUSHKA",
        fontSize = 16.sp,
        color = Color(0xFFF2EBEB),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .size(width = 130.dp, height = 24.dp)
            .background(Color.Transparent) // Если необходим фон, замените на нужный цвет
    )
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoLampushkaTheme {
        MainScreen()
    }
}