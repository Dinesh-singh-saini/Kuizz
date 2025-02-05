package tech.kuizz.mainUi.screens

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.kuizz.R
import tech.kuizz.api.NetworkResponse
import tech.kuizz.api.data.Results
import tech.kuizz.api.view.model.QuizViewModel
import tech.kuizz.formatTime
import tech.kuizz.getTimerDuration
import tech.kuizz.mainUi.components.BoxButton
import tech.kuizz.mainUi.components.QuizAppBar
import tech.kuizz.mainUi.components.ShimmerEffect
import tech.kuizz.mainUi.components.quiz.QuizInterface
import tech.kuizz.onTimeExpired
import tech.kuizz.prepareQuizState

@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel,
    totalQuestions: Int,
    difficulty: String,
    category: String,
    navController: NavController
) {
    val quizData = quizViewModel.quizResult.observeAsState()
    val currentIndex = quizViewModel.currentIndex.observeAsState(0)

    when (val result = quizData.value) {
        is NetworkResponse.Success -> {
            val questionList = result.value.results
            if (questionList.isEmpty()) return

            val quizState = prepareQuizState(questionList[currentIndex.value])
            val shuffledOptions = quizState.shuffledOptions

            Column(modifier = Modifier.fillMaxSize()) {
                HeaderQuiz(category, totalQuestions, difficulty, navController)
                ShowQuestion(quizState.quiz!!, (currentIndex.value + 1).toString())
                QuizInterface(shuffledOptions = shuffledOptions)
                NavigationButtons(currentIndex.value, questionList.size)
            }
        }

        is NetworkResponse.Error -> {
            Text(
                text = "Error: ${result.error}",
                modifier = Modifier.padding(16.dp)
            )
        }

        NetworkResponse.Loading -> {
            Column(modifier = Modifier.fillMaxSize()) {
                HeaderQuiz(category, totalQuestions, difficulty, navController)
                ShimmerEffect()
            }
        }

        null -> {
            Text(
                text = "No data available",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun HeaderQuiz(quizCategory: String, totalQuestions: Int, difficulty: String, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        QuizAppBar( navController, quizCategory)

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Questions: $totalQuestions",
                    modifier = Modifier.padding(8.dp)
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    val timerDuration = getTimerDuration(difficulty, totalQuestions)
                    val remainingTime = remember { mutableStateOf(timerDuration) }
                    val timeFormatted = remember { mutableStateOf(formatTime(timerDuration)) }

                    val timer = remember {
                        object : CountDownTimer(timerDuration, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                remainingTime.value = millisUntilFinished
                                timeFormatted.value = formatTime(millisUntilFinished)
                            }

                            override fun onFinish() {
                                onTimeExpired()
                            }
                        }
                    }

                    LaunchedEffect(Unit) {
                        timer.start()
                    }

                    Text(
                        text = timeFormatted.value,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.weight(.15f))
                Text(
                    text = difficulty.replaceFirstChar { it.uppercaseChar() },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .height(1.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .background(color = Color.Black)
        )
    }
}

@Composable
fun NavigationButtons(currentIndex: Int, size: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when (currentIndex) {
            0 -> {
                BoxButton(
                    buttonText = "Next",
                    borderColour = Color.Black,
                    fraction = 1f,
                    onClick = {  println("next") }
                )
            }
            in 1 until size - 1 -> {
                BoxButton(
                    buttonText = "Previous",
                    onClick = {  },
                    fraction = 0.43f
                )

                BoxButton(
                    buttonText = "Next",
                    borderColour = Color.Black,
                    fraction = 1f,
                    onClick = {  }
                )
            }
            else -> {
                BoxButton(
                    buttonText = "Previous",
                    onClick = {  },
                    fraction = 0.43f
                )

                BoxButton(
                    buttonText = "Submit",
                    onClick = {  },
                    fraction = 1f
                )
            }
        }
    }
}

@Composable
fun ShowQuestion(result: Results, currentIndex: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val decodedText = HtmlCompat.fromHtml(
            result.question ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY
        ).toString()

        Text(
            text = "$currentIndex.",
            fontSize = 18.sp,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(R.color.black)
        )
        Text(
            text = decodedText,
            modifier = Modifier
                .weight(12f)
                .padding(top = 8.dp),
            fontSize = 18.sp,
            style = MaterialTheme.typography.headlineLarge.copy(
                lineHeight = 25.sp
            ),
            color = colorResource(R.color.black)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun QuizScreenPreview() {
    val navController = rememberNavController() // Create a mock NavController for preview
    Column {
        HeaderQuiz(
            quizCategory = "General Knowledge",
            totalQuestions = 4,
            difficulty = "easy",
            navController = navController // Pass the NavController
        )

        ShowQuestion(
            Results(
                category = "General Knowledge",
                type = "multiple",
                difficulty = "easy",
                question = "What is the capital of India?",
                correctAnswer = "New Delhi",
                incorrectAnswers = listOf("Mumbai", "Kolkata", "Chennai")
            ),
            currentIndex = "1"
        )

        QuizInterface(
            shuffledOptions = listOf("New Delhi", "Mumbai", "Kolkata", "Chennai")
        )
    }
}
