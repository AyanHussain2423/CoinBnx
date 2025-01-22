package com.example.coinbnx.Pages

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coinbnx.Component.Button_Btn
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var auth: FirebaseAuth

@Composable
fun SignUpUI(
    navController: NavHostController
) {
    // Initialize Firebase Auth
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    SignUpForm(
        navController = navController,
        username = username,
        password = password,
        onUsernameChange = { username = it },
        onPasswordChange = { password = it }
    )
}

@Composable
fun SignUpForm(
    navController: NavHostController,
    username: TextFieldValue,
    password: TextFieldValue,
    onUsernameChange: (TextFieldValue) -> Unit,
    onPasswordChange: (TextFieldValue) -> Unit
) {
    auth = Firebase.auth
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Username Field
        Text("Username", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = username,
            onValueChange = onUsernameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.medium)
                .padding(16.dp),  // Padding inside the text field
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        Text("Password", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.medium)  // Background with rounded corners
                .padding(16.dp),  // Padding inside the text field
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Signup Button
        Button_Btn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 80.dp, end = 80.dp),
            color = MaterialTheme.colorScheme.onTertiary,
            btn_text = "Signup",
            onClick = {

                // Create a new user with email and password
                auth.createUserWithEmailAndPassword(username.text, password.text)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpUIPreview() {
    // You can't use NavHostController in previews, so passing null here
    SignUpUI(
        navController = rememberNavController() // Use rememberNavController() for preview
    )
}
