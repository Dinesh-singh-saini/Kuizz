package tech.kuizz.mainUi.screens

import android.annotation.SuppressLint
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Test(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "My App",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 28.sp)
            )
        },
        actions = {
            IconButton(onClick = { /* Handle action */ }) {
                Icon(painter = painterResource(id = android.R.drawable.ic_menu_add), contentDescription = "Add")
            }
        }
    )
}
