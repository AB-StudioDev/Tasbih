package com.example.exersise

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exersise.ui.theme.ExersiseTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            ExersiseTheme {

                    MyApp()

            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(){
    val navController = rememberNavController()

    NavHost(navController,
        startDestination = "Home"){
        composable("Home"){ CounterApp(navController)}
        composable("settings"){SettingScreen(navController)}

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController){
    Column {
        TopAppBar(
            title = {Text(text = "Settings")},
            navigationIcon = {IconButton(onClick = {navController.popBackStack()}) {
                Icon( Icons.Default.Home
                , contentDescription = "back")
            }
            }
        )
        Column {
            Text("Settings Screen", fontSize = 24.sp)
        }
    }
}

//@Composable
//fun Column(content: @Composable () -> Column) {
//    TODO("Not yet implemented")
//}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CounterApp(navController: NavController) {
    IconButton(onClick = {navController.navigate("settings")}) {
        Icon(imageVector = Icons.Default.Settings
            ,contentDescription= "settings"
            )
    }

    val infiniteTransition  = rememberInfiniteTransition()
    val glow by infiniteTransition.animateColor(
        initialValue = Color(0xFF42A5F5),
        targetValue = Color(0xFF90CAF9),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500, easing = LinearEasing
                             ),repeatMode= RepeatMode.Reverse
                                           )
    )
    var count by remember { mutableIntStateOf(0) }
    val limit=33
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context,R.raw.button_press1) }
    var phase by remember { mutableIntStateOf(0) }

    val zikrList = listOf(
        "Subhanallah".to(33),
        "Alhamdulillah".to(33),
        "Allahuakber".to (34)
    )

    val currentZikr = zikrList[phase].first

    val currentLimit= zikrList[phase].second
/*ToDo
    Image(
        painter = painterResource(id = R.drawable.oop),
        contentDescription = "background color",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )*/
    Box(modifier = Modifier
        .fillMaxWidth().absolutePadding(left = 190.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = Color.Black
                                        , fontSize = 50.sp)
                ){
                        append("T")
                    }
                    append("asbih ")

                withStyle(
                    style = SpanStyle(color = Color.Black
                        , fontSize = 35.sp)
                ){
                    append("C")
                }
                append("ounter")
            },
            color = Color.Blue
            , fontSize = 20.sp
            , fontWeight = FontWeight.ExtraLight
            , fontStyle = FontStyle.Italic
            , textAlign = TextAlign.Left
            , textDecoration = TextDecoration.Underline
        )

    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Box(
            modifier = Modifier.
                absolutePadding(bottom = 120.dp, right = 1.1.dp).
                size(150.dp)
                        .shadow(8.dp,CircleShape)
                        .background(glow,CircleShape)
            , contentAlignment = Alignment.Center

        ) {


            Text(
                text = " $count/$currentLimit", modifier = Modifier
                    .absolutePadding(right = 25.dp)
                ,style = MaterialTheme.typography.headlineLarge,

                color = Color(0xEAE3E6E7)

            )
        }
        Text(
            text = " $currentZikr", modifier = Modifier
                .absolutePadding(right = 25.dp)
            ,style = MaterialTheme.typography.headlineLarge,

            color = Color(0xFF1565C0)

        )

        Spacer(modifier = Modifier
            .height(70.dp)
            )

        Row(modifier = Modifier.padding(20.dp)) {


            Button(
                onClick = { count = 0 },
                colors = buttonColors(Color.Red),
                shape = CircleShape,
                modifier = Modifier
                    .size(86.dp)


            ) {
                Text(
                    "Reset"
                )
            }
            Spacer(
                modifier = Modifier
                    .width(39.dp)
            )
            Button(
                colors = buttonColors(containerColor = Color(0xFF49525B)),

                onClick = {
                    mediaPlayer.start()

                    if (count < limit) {

                        count++
                    } else {
                        count = 0
                        if (phase < zikrList.size - 1) {
                            phase++
                        } else {
                            phase = 0

                        }
                    }
                },
                shape = CircleShape,
                modifier = Modifier.size(120.dp)
            ) {

                Text(
                    "Tap", style = MaterialTheme.typography.headlineMedium
                )
            }

        }
        }
    }



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}