package tech.kuizz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tech.kuizz.api.view.model.QuizViewModel
import tech.kuizz.mainUi.screens.DashBoard
import tech.kuizz.mainUi.screens.QuizScreen
import tech.kuizz.ui.theme.KuizzTheme
import tech.kuizz.util.navigation.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KuizzTheme {
                MyApp(quizViewModel)
            }
        }
    }
}

@Composable
fun MyApp(quizViewModel: QuizViewModel) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        NavigationGraph(
            navController = navController,
            quizViewModel = quizViewModel
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    quizViewModel: QuizViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard.route
    ) {
        composable(route = Routes.Dashboard.route) {
            DashBoard(
                viewModel = quizViewModel,
                navController = navController
            )
        }

        composable(
            route = "${Routes.QuizScreen.route}/{totalQuestions}/{difficulty}/{category}",
            arguments = listOf(
                navArgument("totalQuestions") { type = NavType.IntType },
                navArgument("difficulty") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions")
            val difficulty = backStackEntry.arguments?.getString("difficulty")
            val category = backStackEntry.arguments?.getString("category")

            QuizScreen(

                quizViewModel = quizViewModel,
                category = category.orEmpty(),
                totalQuestions = totalQuestions ?: 0,
                difficulty = difficulty.orEmpty(),
                navController = navController
            )
        }
    }
}
