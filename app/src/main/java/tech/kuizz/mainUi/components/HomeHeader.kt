package tech.kuizz.mainUi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.kuizz.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeSpHeader() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF00B8D4),
                        Color(0xFF00BFA5)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(
                    bottomStart = 35.dp,
                    bottomEnd = 35.dp
                )
                
            ),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 50.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                painterResource(R.drawable.menu_open_24px),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp),
                tint = colorResource(R.color.black)
            )

            Text(
                text = "Kuizz",
                color = colorResource(R.color.black),
                textAlign = TextAlign.Center,
                fontSize =30.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(3.5f)



            )
            Icon(
                painterResource(R.drawable.account_box_24px),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp),
                tint = colorResource(R.color.black)
            )
        }

    }
}