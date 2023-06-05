package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetpackTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*O bloco setContent define o layout da atividade em que as funções de composição são chamadas. Elas só podem ser chamadas usando outras funções desse tipo.*/
        setContent {
            JetpackTheme() {
                Conversation(SampleData.conversationSample)
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.neide),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary,
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    /*A anotação @Preview permite visualizar as funções de composição no Android Studio sem precisar criar e instalar o app em um emulador ou dispositivo Android.*/
    /*A anotação precisa ser usada em uma função de composição que não use parâmetros. Por esse motivo, não é possível visualizar a função MessageCard diretamente. Em vez disso, crie uma segunda função nomeada como PreviewMessageCard, que chama MessageCard com um parâmetro adequado.*/
    /*@Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )


    @Composable
    fun PreviewMessageCard() {
        AppUpdateTheme {
            Surface {
                MessageCard(
                    msg = Message("Neide", "Take a look at Jetpack Compose, it's great!")
                )
            }
        }
    }*/

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn{
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "Neide",
                "Test...Test...Test..."
            ),
            Message(
                "Neide",
                "List of Android versions:\n" +
                        "Android KitKat (API 19)\n" +
                        "Android Lollipop (API 21)\n" +
                        "Android Marshmallow (API 23)\n" +
                        "Android Nougat (API 24)\n" +
                        "Android Oreo (API 26)\n" +
                        "Android Pie (API 28)\n" +
                        "Android 10 (API 29)\n" +
                        "Android 11 (API 30)\n" +
                        "Android 12 (API 31)\n"
            ),
            Message(
                "Neide",
                "I think Kotlin is my favorite programming language.\n" +
                        "It's so much fun!"
            ),
            Message(
                "Neide",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Neide",
                "Hey, take a look at Jetpack Compose, it's great!\n" +
                        "It's the Android's modern toolkit for building native UI." +
                        "It simplifies and accelerates UI development on Android." +
                        "Less code, powerful tools, and intuitive Kotlin APIs :)"
            ),
            Message(
                "Neide",
                "It's available from API 21+ :)"
            ),
            Message(
                "Neide",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Neide",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Neide",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Neide",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Neide",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Neide",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Neide",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        JetpackTheme(){
            Conversation(SampleData.conversationSample)
        }
    }

}
