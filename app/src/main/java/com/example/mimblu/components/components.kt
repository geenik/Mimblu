package com.example.mimblu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mimblu.R
import com.example.mimblu.model.Plans.Plan
import com.example.mimblu.model.Symptoms.Symptom

@Composable
fun SymptomsRow(symptom: Symptom,ontick:(Int)->Unit,untick:(Int)->Unit) {
    val imageid = remember {
        mutableStateOf(R.drawable.untick)
    }
    val selected = remember {
        mutableStateOf(false)
    }
    if (selected.value) {
        ontick(symptom.id)
        imageid.value = R.drawable.tick
    } else {
        untick(symptom.id)
        imageid.value = R.drawable.untick
    }
    Surface(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = symptom.title)
            Image(painter = BitmapPainter(ImageBitmap.imageResource(imageid.value)),
                contentDescription = "Radio Button",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        selected.value = !selected.value
                    })
        }

    }
}

@Composable
fun PlanRow(plan: Plan) {
    Surface(
        modifier = Modifier
            .width(350.dp)
            .height(380.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, color = Color(0xFFFF8C60))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp
                    )
                ) {
                    append("mimblu ")

                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFFFF8C60)
                    )
                ) {
                    append("Plus")
                }
            })
            Text(
                text = "${plan.duration} Days",
                modifier = Modifier.padding(top = 20.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFF8C60)
            )
            Text(text = "Unlimited message + Voice Notes", modifier = Modifier.padding(top = 20.dp))
            Text(
                text = plan.video_description.split("\n")[0],
                modifier = Modifier.padding(top = 12.dp),
                fontSize = 12.sp,
                color = Color.Gray
            )

            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(

                    )
                ) {
                    append("INR ")

                }
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color(0xFFFF8C60)
                    )
                ) {
                    append(plan.discounted_price+" ")
                }
                withStyle(
                    SpanStyle()
                ) {
                    append(plan.total_price)
                }
            }, modifier = Modifier.padding(top = 20.dp))

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFF8C60)
                )
            ) {
                Text(text = "Continue", modifier = Modifier.padding(horizontal = 70.dp))
            }


            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(

                    )
                ) {
                    append("YOU SAVE INR ")

                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFF8C60)
                    )
                ) {
                    append("${ plan.discounted_price.toInt() - plan.total_price.toInt() } ")
                }
                withStyle(
                    SpanStyle()
                ) {
                    append("WITH THIS PLAN!")
                }
            }, modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally))
        }

    }
}