package tech.kuizz.mainUi.components.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuizInterface(
    shuffledOptions: List<String?>,
    modifier: Modifier = Modifier
) {
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                shuffledOptions.forEachIndexed { index, optionText ->
                    if (optionText?.isNotEmpty() == true) {
                        val isSelected = selectedOptionIndex == index

                        QuizOptions(
                            optionNumber = ('A' + index).toString(),
                            optionText = optionText,
                            selected = isSelected,
                            onOptionSelected = {
                                selectedOptionIndex = if (isSelected) null else index
                            },
                            onOptionUnselected = {
                                selectedOptionIndex = null
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun QuizInterfacePreview() {
    QuizInterface(
        shuffledOptions = listOf("New Delhi", "Mumbai", "Kolkata", "Chennai")
    )
}
