package tech.kuizz.mainUi.components.quiz

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*

import tech.kuizz.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewQuizOptions() {
    QuizOptions(
        optionNumber = "A",
        optionText = "New Delhi",
        selected = false,
        onOptionSelected = {},
        onOptionUnselected = {}
    )
}

@Composable
fun QuizOptions(
    optionNumber: String,
    optionText: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    onOptionUnselected: () -> Unit
) {
    val textColor = if (selected) androidx.compose.ui.graphics.Color.White else androidx.compose.ui.graphics.Color.Black

    val transition = updateTransition(targetState = selected, label = "SelectedTransition")
    val backgroundColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = 500, easing = LinearEasing) },
        label = "BackgroundColorAnimation"
    ) { isSelected ->
        if (isSelected) colorResource(R.color.teal_700) else colorResource(R.color.teal_200)
    }

    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(35.dp))
            .noRippleClickable {
                if (selected) onOptionUnselected() else onOptionSelected()
            }
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .shadow(10.dp, CircleShape, clip = true, ambientColor = colorResource(R.color.black))
                    .clip(CircleShape)
                    .background(colorResource(R.color.white)),
                contentAlignment = Alignment.Center
            ) {
                if (selected) {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.clicked))
                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = 1
                    )
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        modifier = Modifier.size(60.dp)
                    )
                } else {
                    Text(
                        text = optionNumber,
                        color = textColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .weight(0.6f)
            )

            Text(
                modifier = Modifier.weight(7.1f),
                text = optionText,

                fontSize = 16.sp,
                maxLines = 3,
                color = textColor
            )
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}
