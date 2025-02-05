package tech.kuizz.api

import tech.kuizz.api.data.Results


sealed class NetworkResponse<out T> {

    data class Success<out T>(val value: T) : NetworkResponse<T>()
    data class Error(val error: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}

class QuizState(
    val quiz: Results? = null,
    val shuffledOptions: List<String?> = emptyList(),
    val selectedOption: Int? = null
)



