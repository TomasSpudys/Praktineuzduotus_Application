package com.example.demo_application_tomas.ui.theme.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demo_application_tomas.R
import androidx.compose.material.icons.rounded.ShoppingCart

/*
    Antra uzduoties dalis:
*/


val GAVIMO_LANGAS = "Gavimas"
val SIUNTIMO_LANGAS = "Siunta"

@Composable
fun LoggedInView() {

    var activeWindow by remember { mutableStateOf(GAVIMO_LANGAS) }  // <--- Kintamasis, kuris parodo kuris langas yra active

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        if (activeWindow == GAVIMO_LANGAS) {
            GavimoVaizdas()
        } else if (activeWindow == SIUNTIMO_LANGAS) {
            SiuntaVaizdas()
        }

        // * Paspaudus skirtinga buttona pasikeicia activeWindow t.y. pasikeicia aktyvus langas
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { activeWindow = GAVIMO_LANGAS }) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(androidx.core.R.drawable.ic_call_answer),
                        contentDescription = "IDK"
                    )
                    Text(text = "GAVIMAS", color = Color.White)
                }
            }
            Button(onClick = { activeWindow = SIUNTIMO_LANGAS }) {
                Text(text = "Siunta")
            }
        }
    }
}


@Composable
fun GavimoVaizdas() {
    GavimoListas()
}

@Composable
fun GavimoListas() {
    var listPart = arrayListOf<CarPart>()
    val item1= CarPart(1,"variklis","bmw_m5",R.drawable.bmw_m5)
    listPart.add(item1) //idedu lista CarPark objektui//
    val item2= CarPart(2,"variklis","audi_a6", R.drawable.audi_a6)
    listPart.add(item2)
    val item3= CarPart(3,"variklis","toyota_camry", R.drawable.toyota_camry)
    listPart.add(item3)
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8F)
        .verticalScroll(rememberScrollState())
        ,verticalArrangement = Arrangement.spacedBy(10.dp)) {
        listPart.forEach { item ->
            GavimoListItem(item)
        }
    }
}

@Composable
fun GavimoListItem(carPart:CarPart) {
    Row(
        modifier = Modifier.padding(start = 20.dp)
            .border(5.dp, Color.Blue)
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Image(painterResource(carPart.image),contentDescription = "IDK",modifier = Modifier.size(100.dp))
        Text(text =carPart.id.toString() , color = Color.Black)
        Text(text=carPart.carMaker)
        Button(onClick = { }) {
            Text(text = "Gavimo btn")
            Icon (
                Icons.Rounded.CalendarMonth,
                modifier = Modifier.size(16.dp),
                contentDescription = "icon",
                tint= Color.Red)
        }
    }
}


@Composable
fun SiuntaVaizdas() {
    Column(
        modifier = Modifier
            .size(700.dp)
            .border(2.dp, MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Rounded.ShoppingCart,
            modifier = Modifier.size(64.dp),
            contentDescription = "icon",
            tint = Color.Blue
        )

        }
    }


