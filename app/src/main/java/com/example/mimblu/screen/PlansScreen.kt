package com.example.mimblu.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mimblu.components.PlanRow

@Composable
fun PlansScreen(navController: NavController, string: String?) {
    val viewModel: PlansViewModel = viewModel()
    val data = viewModel.plans.collectAsState(null)
    val list: MutableList<Int> = mutableListOf()
    string?.split(",")?.forEach {
        if(it.isNotEmpty()) {
            list.add(it.toInt())
        }
    }
    Log.d("TAG", list.size.toString())
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button",
                    modifier = Modifier.clickable { navController.popBackStack() })

                Text(
                    text = "mimblu",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFF8C60)
                )
                Box {}
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Pick your plan to continue",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )
            Text(
                text = "Mimblu is up to 5x cheaper",
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Gray
            )
            Text(text = "than in-person therapy", color = Color.Gray)
            Surface(
                color = Color(0xFFECECEC), modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (data.value != null) {
                        LazyRow {
                            items(data.value?.list!!) {
                                PlanRow(plan = it)
                            }
                        }
                    }
                }
            }


        }
    }
}