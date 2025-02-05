package tech.kuizz.util.navigation

sealed class Routes(val route: String) {
    object Dashboard : Routes("dashboard")
    object QuizScreen : Routes("quiz_screen")
    object QuizResult : Routes("quiz_result")
    object QuizSettings : Routes("quiz_settings")
    object QuizSettingsResult : Routes("quiz_settings_result")
    object QuizSettingsCategory : Routes("quiz_settings_category")
}
