package com.example.dynamicform.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownComponent(
    options: List<String>,
    isExpanded: Boolean,
    onExpandedChanged: () -> Unit,
    onDismiss: () -> Unit,
    selectedText: String,
    onOptionSelected: (String, Int) -> Unit,
) {

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { onExpandedChanged() },
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onDismiss() }) {
            options.forEachIndexed { index, dropDownMenuItems ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(), text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = dropDownMenuItems)
                    }

                }, onClick = {
                    onOptionSelected(options[index], index)
                },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
