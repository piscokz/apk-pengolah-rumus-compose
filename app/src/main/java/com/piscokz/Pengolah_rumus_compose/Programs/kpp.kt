package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

val lebarTexfield = 120
val marginBawah = 20
val marginBawahSesi = 40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun kpp(
    navController: NavController
) {
    PengolahRumusComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                color = switchColor(),
                                text = listRumus[0],
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge

                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )

                }

            ) { paddingValues ->

                var panjang by remember { mutableStateOf("") }
                var lebar by remember { mutableStateOf("") }

                var display2 by remember {
                    mutableStateOf("")
                }

                var tekanTombolHitung by remember {
                    mutableStateOf(false)
                }

                var expandedPanjang by remember { mutableStateOf(false) }
                var expandedLebar by remember { mutableStateOf(false) }
                var expandedHitung by remember { mutableStateOf(false) }

                var ukuranInputPanjang by remember { mutableStateOf("cm") }
                var ukuranInputLebar by remember { mutableStateOf("cm") }
                var ukuranInputHitung by remember { mutableStateOf("cm") }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {

                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(marginBawah.dp)
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        ) {

                            Text(
                                color = switchColor(),
                                text = displayKpp(
                                    display2,
                                    panjang,
                                    lebar,
                                    ukuranInputPanjang,
                                    ukuranInputLebar
                                ),
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Thin,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .border(1.dp, switchColor())
                                    .padding(15.dp),
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic,
                            )

                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(marginBawah.dp)
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Row {
                                    OutlinedTextField(
                                        value = panjang,
                                        onValueChange = { panjang = it },
                                        label = {
                                            Text(
                                                color = switchColor(),
                                                text = "panjang",
                                                fontFamily = FontFamily.Serif,
                                                modifier = Modifier.fillMaxWidth(),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                        },
                                        textStyle = LocalTextStyle.current.copy(
                                            textAlign = TextAlign.Right
                                        ),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        singleLine = true,
                                        modifier = Modifier
                                            .width(lebarTexfield.dp),
                                        suffix = {
                                            Text(
                                                color = switchColor(),
                                                text = " $ukuranInputPanjang",
                                                fontFamily = FontFamily.Monospace,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        },
                                        supportingText = {
                                            if (panjang.isEmpty()) Text(
                                                text = "masukkan angka",
                                                color = switchColor()
                                            )
                                        },
                                        isError = panjang.isEmpty() && tekanTombolHitung
                                    )
                                    IconButton(onClick = { expandedPanjang = true }) {
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedPanjang,
                                        onDismissRequest = { expandedPanjang = false }
                                    ) {
                                        for (i in listUkuranPanjang) {
                                            DropdownMenuItem(
                                                text = { Text(text = i) },
                                                onClick = { ukuranInputPanjang = i })
                                        }
                                    }
                                }
                                Row {
                                    DropdownMenu(
                                        expanded = expandedLebar,
                                        onDismissRequest = { expandedLebar = false }
                                    ) {
                                        for (i in listUkuranPanjang) {
                                            DropdownMenuItem(
                                                text = { Text(text = i) },
                                                onClick = { ukuranInputLebar = i })
                                        }
                                    }
                                    IconButton(onClick = { expandedLebar = true }) {
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = "Localized description"
                                        )
                                    }
                                    OutlinedTextField(
                                        value = lebar,
                                        onValueChange = { lebar = it },
                                        label = {
                                            Text(
                                                color = switchColor(),
                                                text = "lebar",
                                                fontFamily = FontFamily.Serif,
                                                modifier = Modifier.fillMaxWidth(),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold

                                            )
                                        },
                                        textStyle = LocalTextStyle.current.copy(
                                            textAlign = TextAlign.Right
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number,
                                        ),
                                        singleLine = true,
                                        modifier = Modifier
                                            .width(lebarTexfield.dp)
                                            .pointerInput(key1 = true) {
                                            },
                                        suffix = {
                                            Text(
                                                color = switchColor(),
                                                text = " $ukuranInputLebar",
                                                fontFamily = FontFamily.Monospace,
                                                style = MaterialTheme.typography.titleMedium,
                                            )
                                        },
                                        supportingText = {
                                            if (lebar.isEmpty()) Text(
                                                text = "masukkan angka",
                                                color = switchColor()
                                            )
                                        },
                                        isError = lebar.isEmpty() && tekanTombolHitung
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawah.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawah.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = { expandedHitung = true }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Localized description"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedHitung,
                                    onDismissRequest = { expandedHitung = false }
                                ) {
                                    for (i in listUkuranPanjang) {
                                        DropdownMenuItem(
                                            text = { Text(text = i) },
                                            onClick = { ukuranInputHitung = i })
                                    }
                                }
                                ElevatedButton(
                                    shape = RoundedCornerShape(35.dp),
                                    colors = switchButtonColors(),
                                    onClick = {
                                        if(cekInput(panjang,lebar)) {
                                            tekanTombolHitung = panjang.isEmpty() || lebar.isEmpty()
                                            if (panjang.isNotEmpty() && lebar.isNotEmpty()) {
                                                panjang = konversiUkuranPanjang(
                                                    ukuranInputPanjang,
                                                    ukuranInputHitung,
                                                    panjang
                                                )
                                                lebar = konversiUkuranPanjang(
                                                    ukuranInputLebar,
                                                    ukuranInputHitung,
                                                    lebar
                                                )
                                                display2 = kpp(panjang, lebar, ukuranInputHitung)
                                                panjang = ""
                                                lebar = ""
                                            }
                                            else {
                                                tekanTombolHitung = true
                                            }
                                        }
                                    },
                                    modifier = Modifier.padding(end = 10.dp),
                                ) {
                                    Text(
                                        color = switchColor(),
                                        text = "Hitung & konversikan $ukuranInputHitung",
                                        textAlign = TextAlign.Center
                                    )
                                }
                                ElevatedButton(
                                    shape = RoundedCornerShape(35.dp),
                                    colors = switchButtonColors(),
                                    onClick = {
                                        panjang = ""
                                        lebar = ""
                                        display2 = ""
                                        tekanTombolHitung = false
                                    },
                                ) {
                                    Text(
                                        color = switchColor(),
                                        text = "Reset"
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawahSesi.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(1.dp, switchColor())
                            )
                        }
                    }
                }
            }
        }
    }
}

