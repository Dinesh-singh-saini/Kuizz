package tech.kuizz

import android.annotation.SuppressLint
import tech.kuizz.api.QuizState
import tech.kuizz.api.data.Results

fun prepareQuizState(results: Results): QuizState {
    val options = (results.incorrectAnswers + results.correctAnswer).shuffled()

    return QuizState(
        quiz = results,
        shuffledOptions = options
    )
}

@SuppressLint("DefaultLocale")
fun formatTime(millis: Long): String {
    val seconds = (millis / 1000).toInt()
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

fun getTimerDuration(difficulty: String, totalQuestions: Int): Long {
    return when (difficulty.lowercase()) {
        "easy" -> 60000L * totalQuestions
        "medium" -> 50000L * totalQuestions
        "hard" -> 45000L * totalQuestions
        else -> 52000L * totalQuestions
    }
}

fun onTimeExpired(){
    println("Time expired")
}
