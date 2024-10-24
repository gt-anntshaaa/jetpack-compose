package com.example.compose1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose1.ui.theme.Compose1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose1Theme {
                MainScreen()
//                Surface(color = MaterialTheme.colorScheme.tertiary) {
//                    MainScreen()
//                }
            }
        }
    }
}



@Composable
fun CustomButton(context: Context, text: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(onClick = {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
            ) {
            Text(text = "Button", fontSize = 14 .sp)

        }
        
        //Spacer(modifier = Modifier.height(10 .dp))
    }
}

@Composable
fun CustomTextField(text: String, onTextChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {onTextChange(it)},
            label = {
                Text(text = "Enter your text")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomTextView(text: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome $text",
            modifier = modifier,
            color = Color.Black,
            fontStyle = FontStyle.Italic,

            style = TextStyle(
                background = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16 .dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintLayout(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (topBar, companyName, customText, button) = createRefs()

        TopAppBar(
            modifier = Modifier
                .constrainAs(topBar){
                    top.linkTo(parent.top)

                    start.linkTo(parent.start)

                    end.linkTo(parent.end)
                },
            title = {
                Text(
                    text = "My App",
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Magenta)
        )

        Text(
            text = "Hello Compose",
            color = Color.White,
            modifier = Modifier
                .constrainAs(companyName){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topBar.bottom, margin = 100 .dp)
                   // bottom.linkTo(parent.bottom)
                }
        )
        
        CustomTextView(
            text = "Margaret",
            modifier = Modifier
                .constrainAs(customText){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(companyName.bottom)
                }
            )

        CustomButton(context = LocalContext.current, text = "compose",
            modifier = Modifier
                .constrainAs(button){
                    start.linkTo(parent.start)
                    top.linkTo(customText.bottom)
                }
        )
    }
}

@Composable
fun MainScreen(){
    ConstraintLayout()

//    var text by remember {
//        mutableStateOf("")
//    }

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        CustomTextView(text = "Jacob")
//        CustomTextView(text = "Amala")
//        CustomButton(context = LocalContext.current, text = text)
//        CustomTextField(text, onTextChange = {text = it})
//    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview(){
    MaterialTheme{
        MainScreen()
    }
}

