package tech.kuizz.mainUi.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.kuizz.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstantsDropdownMenu(
    menuTitle: String,
    menuItems: List<String>,
    onItemSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(menuItems[0]) }

    Column(modifier = Modifier.padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = menuTitle) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                shape = RoundedCornerShape(8.dp),
//                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedItem = item
                            isExpanded = false
                            onItemSelected(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConstantsDropdown() {
    MaterialTheme {
        Column {
            ConstantsDropdownMenu(
                menuTitle = "Select Category",
                menuItems = Constants.categories,
                onItemSelected = { selectedCategory ->
                    // Handle the selected category
                    println("Selected category: $selectedCategory")
                }
            )
            ConstantsDropdownMenu(
                menuTitle = "Select Difficulty",
                menuItems = Constants.difficulty,
                onItemSelected = { selectedDifficulty ->
                    // Handle the selected difficulty
                    println("Selected difficulty: $selectedDifficulty")
                }
            )
        }
    }
}
