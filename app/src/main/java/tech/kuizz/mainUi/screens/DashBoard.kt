package tech.kuizz.mainUi.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import tech.kuizz.api.view.model.QuizViewModel
import tech.kuizz.mainUi.components.BoxButton
import tech.kuizz.mainUi.components.ConstantsDropdownMenu
import tech.kuizz.mainUi.components.HomeSpHeader
import tech.kuizz.util.Constants
import tech.kuizz.util.Constants.categoriesMap
import tech.kuizz.util.navigation.Routes
import java.util.Locale


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoard(
    viewModel: QuizViewModel,
    navController: NavHostController
) {
    Column {

        var selectedCategory by remember { mutableStateOf<String?>(null) }
        var selectedDifficulty by remember { mutableStateOf<String?>(null) }
        var selectedType by remember { mutableStateOf<String?>(null) }
        var selectedNumber by remember { mutableStateOf(Constants.numberAsString.firstOrNull()) }


        HomeSpHeader()

        ConstantsDropdownMenu(
            menuTitle = "Select Number of Questions",
            menuItems = Constants.numberAsString,
            onItemSelected = {
                selectedNumber = it
            }
        )
        ConstantsDropdownMenu(
            menuTitle = "Select Category",
            menuItems = Constants.categories,
            onItemSelected = {
                selectedCategory = it
            }
        )

        ConstantsDropdownMenu(
            menuTitle = "Select Difficulty",
            menuItems = Constants.difficulty,
            onItemSelected = {
                selectedDifficulty = it
            }
        )
        ConstantsDropdownMenu(

            menuTitle = "Select Type",
            menuItems = Constants.type,
            onItemSelected = {
                selectedType = it
            }
        )

        BoxButton(
            onClick = {
                Log.d(
                    "TAG",
                    "DashBoard: $selectedCategory $selectedDifficulty $selectedType $selectedNumber"
                )

                val categoryId = categoriesMap.firstOrNull { it.first == selectedCategory }?.second

                val difficulty =
                    if (selectedDifficulty?.isNotBlank() == true && selectedDifficulty != "Any Difficulty")
                        selectedDifficulty?.lowercase(Locale.getDefault())
                    else null

                val type = when (selectedType) {
                    "Multiple Choice" -> "multiple"
                    "True / False" -> "boolean"
                    else -> null
                }

                selectedNumber?.let {
                    viewModel.getQuestion(
                        amount = it.toInt(),
                        category = categoryId,
                        difficulty = difficulty,
                        type = type
                    )
                }

                navController.navigate(
                    "${Routes.QuizScreen.route}/${selectedNumber ?: 0}/${difficulty ?: "Mixed"}/${selectedCategory?: "Mixed Category"}"
                )


                // For debugging, log the generated URL
                val urlBuilder = StringBuilder("https://opentdb.com/api.php?amount=$selectedNumber")
                categoryId?.let { urlBuilder.append("&category=$it") }
                difficulty.let { urlBuilder.append("&difficulty=$it") }
                type.let { urlBuilder.append("&type=$it") }
                println("Generated API URL: $urlBuilder")
            },
            buttonText = "Generate Quiz"
        )
    }
}