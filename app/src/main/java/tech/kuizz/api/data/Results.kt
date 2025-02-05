package tech.kuizz.api.data

import com.google.gson.annotations.SerializedName


data class Results(

    @SerializedName("type"              ) var type: String?           = null,
    @SerializedName("difficulty"        ) var difficulty: String?           = null,
    @SerializedName("category"          ) var category: String?           = null,
    @SerializedName("question"          ) var question: String?           = null,
    @SerializedName("correct_answer"    ) var correctAnswer: String?           = null,
    @SerializedName("incorrect_answers" ) var incorrectAnswers: List<String> = arrayListOf()

)