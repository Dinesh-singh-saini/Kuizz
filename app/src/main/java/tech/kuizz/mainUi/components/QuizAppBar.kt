package tech.kuizz.mainUi.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import tech.kuizz.R
import tech.kuizz.util.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizAppBar(
    navController: NavController,
    quizCategory: String,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF00B8D4)),

        title = {
            Text(
                text = quizCategory,
                maxLines = 1,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                // Use navController to navigate back to the Dashboard screen
                navController.popBackStack(Routes.Dashboard.route, inclusive = false)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_24px),
                    contentDescription = "Back"
                )
            }
        }
    )
}
