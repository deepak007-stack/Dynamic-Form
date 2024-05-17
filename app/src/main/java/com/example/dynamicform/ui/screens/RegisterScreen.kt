package com.example.dynamicform.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dynamicform.model.Data
import com.example.dynamicform.ui.component.DropdownComponent
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(dataList: List<Data>) {

    val options = mutableListOf<String>()
    val names = mutableListOf<String>()
    val fieldValues = mutableListOf<String>()

    val focusRequester = remember { FocusRequester() }
    val secondFocusRequester = remember { FocusRequester() }
    val thirdFocusRequester = remember { FocusRequester() }
    val fourthFocusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        delay(300)
        focusRequester.requestFocus()
    }
    dataList.forEach {
        options.add(it.FieldName)
        names.add(it.SNo.toString())
        fieldValues.add(it.FieldValue)
    }

    val context = LocalContext.current
    val genders = listOf("male", "female")
    var isExpanded by remember { mutableStateOf(false) }
    var isExpanded2 by remember { mutableStateOf(false) }
    var isExpanded3 by remember { mutableStateOf(false) }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    var selectedGender by rememberSaveable { mutableStateOf("") }
    var selectedText by remember { mutableStateOf("Select your field name:-") }
    var selectedText2 by remember { mutableStateOf("Select your SNo:-") }
    var selectedText3 by remember { mutableStateOf("Select your Field Value:-") }
    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var isNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var isAgeError by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Register",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 20.dp, bottom = 20.dp, start = 4.dp, end = 4.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Enter your name") },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth(),
                isError = isNameError,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    isNameError = name == ""
                    if (!isNameError) {
                        secondFocusRequester.requestFocus()
                    }
                }),
                supportingText = {
                    if (isNameError) {
                        Text(text = "Name can't be Empty!")
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Enter your email") },
                modifier = Modifier
                    .focusRequester(secondFocusRequester)
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth(),
                isError = emailError,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    emailError = email == "" || !email.contains(".com")

                    if (!emailError) {
                        thirdFocusRequester.requestFocus()
                    }
                }),
                supportingText = {
                    if (emailError) {
                        Text(text = "Please enter a proper Email!")
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(text = "Enter your age") },
                modifier = Modifier
                    .focusRequester(thirdFocusRequester)
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth(),
                isError = isAgeError,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onNext = {
                    isAgeError = age == ""
                    if (!isAgeError) {
                        fourthFocusRequester.requestFocus()
                    }
                }),
                supportingText = {
                    if (isAgeError) {
                        Text(text = "Please enter a proper Age!")
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(text = "Enter your Address") },
                modifier = Modifier
                    .focusRequester(fourthFocusRequester)
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    fourthFocusRequester.freeFocus()
                })
            )


            Spacer(modifier = Modifier.height(14.dp))

            DropdownComponent(
                options = options,
                isExpanded = isExpanded,
                onExpandedChanged = { isExpanded = !isExpanded },
                onDismiss = { isExpanded = false },
                selectedText = selectedText,
                onOptionSelected = { text, index ->
                    selectedText = text
                    selectedText2 = names[index]
                    selectedText3 = fieldValues[index]
                    isExpanded = false
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            DropdownComponent(
                options = names,
                isExpanded = isExpanded2,
                onExpandedChanged = { isExpanded2 = !isExpanded2 },
                onDismiss = { isExpanded2 = false },
                selectedText = selectedText2,
                onOptionSelected = { text, index ->
                    selectedText2 = text
                    selectedText = options[index]
                    selectedText3 = fieldValues[index]
                    isExpanded2 = false
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            DropdownComponent(
                options = fieldValues,
                isExpanded = isExpanded3,
                onExpandedChanged = { isExpanded3 = !isExpanded3 },
                onDismiss = { isExpanded3 = false },
                selectedText = selectedText3,
                onOptionSelected = { text, index ->
                    selectedText3 = text
                    selectedText2 = names[index]
                    selectedText = options[index]
                    isExpanded3 = false
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Select you gender:- ",
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                genders.forEach { gender ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .clickable { selectedGender = gender },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RadioButton(
                            selected = selectedGender == gender,
                            onClick = { selectedGender = gender }
                        )
//                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = gender,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }

            if (selectedImageUri != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Selected Image = " + selectedImageUri.toString(),
                    modifier = Modifier.padding(horizontal = 14.dp),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            OutlinedButton(
                onClick = {
                    launcher.launch("image/*")
                }, modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Upload an Image")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        if (isNameError || isAgeError || emailError || selectedGender == "" || selectedText == "Select your field name:-" || selectedText2 == "Select your SNo:-" || selectedText3 == "Select your Field Value:-" || selectedImageUri == null)
                            "Please fill all details Correctly!" else "Form Submitted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }, modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 0.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "SUBMIT")
            }
        }
    }
}