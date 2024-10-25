package com.project.cartapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.project.cartapp.ui.theme.CartAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val people = listOf(
            Person(firstName = "Ritesh", lastName = "Yadav", age = 31),
            Person(firstName = "Shivangi", lastName = "Pradeep", age = 29)
        )
        val filteredPeople = people.filter { it.age > 30 }
        val rssItems = listOf(

            RSSItem(
                title = "Article 1 Title",
                text = "This is the description of article 1.",
                type = "blog"
            ),
            RSSItem(
                title = "Article 2 Title",
                text = "This is the description of article 2.",
                type = "blog"
            ),
            RSSItem(
                title = "Article 3 Title",
                text = "This is the description of article 3.",
                type = "video"
            ),
            RSSItem(
                title = "Article 4 Title",
                text = "This is the description of article 4.",
                type = "blog"
            )
        )
        setContent {
            CartAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    LazyColumn {
                        items(rssItems) {
//                            CardView(it)
                            RSSItemText(it)
                        }
                    }
                }
            }
        }
    }
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
    CartAppTheme {
        Greeting("Android")
    }
}
@Composable
fun CardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Photo of ${person.firstName}",
                modifier = Modifier
                    .padding(12.dp)
                    .height(100.dp)
                    .width(100.dp)
            )
            Column {
                Text(
                    text = "${person.firstName} ${person.lastName}",
                    modifier = Modifier.padding(start = 12.dp, top = 30.dp)
                )
                Text(
                    text = "${person.age} years",
                    modifier = Modifier.padding(12.dp, top = 2.dp)
                )
            }
        }
    }
}
@Composable
fun RSSItemText(rssItem: RSSItem){


}