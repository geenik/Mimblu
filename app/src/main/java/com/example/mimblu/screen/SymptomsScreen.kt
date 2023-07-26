package com.example.mimblu.screen

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mimblu.components.SymptomsRow
import com.example.mimblu.model.Symptoms.Symptom
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SymptomsScreen(navController: NavController) {
    val viewmodal = viewModel<SymptomsviewModel>()
    val symptoms by viewmodal.symptoms.collectAsState(null)
    val list:MutableState<Set<Int>> = remember{ mutableStateOf(emptySet()) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "mimblu",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFF8C60)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "What led you to Mimblu Today?",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
            Text(text = "Help us find you the best selection of", modifier = Modifier.padding(top = 10.dp), color = Color.Gray)
            Text(text = "Counsellors to chose from", color = Color.Gray)
            Surface(Modifier.fillMaxHeight(0.85F)){
                if (symptoms != null) {
                    LazyColumn {
                        items(symptoms?.list!!) {
                            SymptomsRow(it, ontick = {
                                 list.value=list.value+it
                            }, untick = {element->
                                val updatedList = list.value.filter { it != element }
                                list.value= updatedList.toSet()
                            })
                        }
                    }
                }
            }
            Button(
                enabled = list.value.isNotEmpty(),
                onClick = {val cur=list.value
                    var id=""
                    cur.forEach{
                        id+=it.toString()
                        id+=","
                    }
                    navController.navigate("plans/$id")},
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFF8C60)
                )
            ) {
                Text(text = "Show Me My Matches", modifier = Modifier.padding(horizontal = 40.dp))
            }


        }

    }

}