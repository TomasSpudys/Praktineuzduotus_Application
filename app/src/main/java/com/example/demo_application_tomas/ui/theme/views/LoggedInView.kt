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
import androidx.compose.material.icons.rounded.DeleteSweep
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
val SIUNTIMO_LANGAS = "Siuntimas"

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
            SiuntimoVaizdas()
        }

        // * Paspaudus skirtinga buttona pasikeicia activeWindow t.y. pasikeicia aktyvus langas
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { activeWindow = GAVIMO_LANGAS }) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(
                        painterResource(androidx.core.R.drawable.ic_call_answer),
                        contentDescription = "IDK"
                    )
                    Text(text = "GAVIMAS", color = Color.Red)
                }
            }
            Button(onClick = { activeWindow = SIUNTIMO_LANGAS }) {
                Text(text = "Siuntimas")
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
    val simple_list = listOf<String>("Bmw", "Audi", "Toyota", "Lioxa", "VW", "Kia", "Audivel")
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8F)
        .verticalScroll(rememberScrollState())
        ,verticalArrangement = Arrangement.spacedBy(10.dp)) {
        simple_list.forEach { item ->
            GavimoListItem(item)
        }
    }
}

@Composable
fun GavimoListItem(item: String) {
    Row(
        modifier = Modifier
            .border(5.dp, MaterialTheme.colorScheme.onTertiary)
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Image(painterResource(R.drawable.bmw_f10), contentDescription = "IDK",modifier = Modifier.size(100.dp))
        Text(text = item, color = Color.Red)
        Button(onClick = { }) {
            Text(text = "Gavimo btn")
            Icon (
                Icons.Rounded.DeleteSweep,
                modifier = Modifier.size(16.dp),
                contentDescription = "icon",
                tint= Color.Red)
        }
    }
}


@Composable
fun SiuntimoVaizdas() {
    Column(
        modifier = Modifier
            .size(700.dp)
            .border(2.dp, MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon (
            Icons.Rounded.ShoppingCart,
            modifier = Modifier.size(64.dp),
            contentDescription = "icon",
            tint= Color.Red
        )
    }
}

