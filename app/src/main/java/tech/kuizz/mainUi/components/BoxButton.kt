package tech.kuizz.mainUi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BoxButton(
    buttonText: String,
    onClick: () -> Unit,
    fraction : Float = 1f,
    borderColour: Color = Color.Transparent,
    textColor: Color = Color.Black
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(fraction)
        .height(48.dp)
        .clip(RoundedCornerShape(12.dp))
        .border(
            width = 1.dp,
            color = borderColour,
            shape = RoundedCornerShape(12.dp)
        )
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF00BFA5),
                    Color(0xFF00B8D4)
                )
            )
        )
        .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = buttonText,
            fontSize = 20.sp,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = textColor
        )
    }
}
