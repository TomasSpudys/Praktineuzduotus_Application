package com.example.demo_application_tomas.ui.theme.views

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


/*

    Pirma uzduoties dalis:
 */

@Composable
fun ScanView(onPasswordChange: (String) -> Unit) {
    var password_scanned = ""

    // Decalinam kintamuosius:
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var textToGenerate by remember { mutableStateOf("") }

    fun scanQr(qrBitmap: Bitmap){
        val options = BarcodeScannerOptions.Builder()  // <-- Pasikeliam BarcodeScannerOptions objekta
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()

        val image = qrBitmap?.let {  // <---- Konvertuojam Bitmap image i InputImage objekta
            InputImage.fromBitmap(it, 0)
        }
        val scanner =
            BarcodeScanning.getClient(options)  // <------ Pasikeliam BarcodeScanning objekta, paduodam options


        val result = scanner.process(image!!)    // <------ Rezultato kintamasis, paduodam jam image
            // Jeigu viskas pasiseka:
            .addOnSuccessListener { barcodes ->
                // barcodes  --> gaunam masyva (kelis barkodus), musu atveju nes vienas image
                for (barcode in barcodes) {
                    if (barcode.valueType == Barcode.TYPE_TEXT) {   // <---- Is QR barkodo pasiimam text reiksme
                        val rawValue = barcode.rawValue
                        if (rawValue != null) {
                            // Jeigu viskas okay priskiriam tekstine QR barkodo reiksme valueToReturn kintamajam
                            password_scanned = rawValue
                            onPasswordChange(password_scanned)
                        }
                    }
                }
            }
            // Jeigu erroras:
            .addOnFailureListener {
                password_scanned = "ERROR"
            }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // qrBitmap? <--- Sita vieta nesiexecutina iki kol qrBitmap kintamasis == null
        qrBitmap?.let {
            // Renderinam Image componeneta:
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

        // Renderinam Texto ivedimo componeneta:
        TextField(value = textToGenerate,
            // onValueChange , i si metoda kreipiamasi kai pasikeicia tekstas
            onValueChange = {
                textToGenerate = it
                qrBitmap = generateQrCode(textToGenerate)
            })


        // Button elementas prisiloginimui
        Button(onClick = {
            scanQr(qrBitmap!!)
        }) {
            Text(text = "LOGIN")
        }
    }
}


fun generateQrCode(textValue: String): Bitmap? {
    try {
        val barcodeEncoder = BarcodeEncoder() // <---- Kuriam barcodeEncoder objekta
        val qr_barcode = barcodeEncoder  // <------- QR objektas/kintamasis
            .encodeBitmap(
                textValue, BarcodeFormat.QR_CODE, 400, 400
            )
        return qr_barcode
    } catch (e: Exception) {
        return null
    }
}



