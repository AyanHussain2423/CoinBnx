package com.example.coinbnx.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.EditRoad
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Track the selected index
    val selectedIndex = remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background) // Use Material background color
    ) {
        // Divider at the top of the bottom bar
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
        )

        // Content Layer (Icons and Texts)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            bottomBarItems.forEachIndexed { index, item ->
                val isSelected = selectedIndex.value == index

                // Box to contain both the Icon and Text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .size(56.dp) // Size for better spacing
                        .clip(RoundedCornerShape(14.dp)) // Clip the Box with rounded corners
                        .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else Color.Transparent) // Light background for selected
                        .border(
                            width = 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent, // Border color for selected
                            shape = RoundedCornerShape(14.dp)
                        ) // Apply border with rounded corners
                        .clickable {
                            selectedIndex.value = index
                            when (index) {
                                0 -> navController.navigate("home") // Navigate to Home screen
                               1 -> navController.navigate("invest_page") // Navigate to Invest screen
                               3 -> navController.navigate("portfolio_page") // Navigate to Portfolio screen
                            }
                        }
                        .padding(8.dp), // Add padding for better alignment
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground, // Change icon color based on selection
                        modifier = Modifier.size(24.dp)
                            .clip(RoundedCornerShape(14.dp)) // Clip icon inside the rounded border
                    )

                    // Title Text below the icon
                    Text(
                        text = item.title,
                        style = TextStyle(
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                            fontSize = 10.sp // Small font size for the label
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean = false
)

val bottomBarItems = listOf(
    BottomBarItem(
        title = "Home",
        icon = Icons.Rounded.Home
    ),
    BottomBarItem(
        title = "Invest",
        icon = Icons.Rounded.AttachMoney
    ),
    BottomBarItem(
        title = "Futures",
        icon = Icons.Rounded.EditRoad
    ),
    BottomBarItem(
        title = "Portfolio",
        icon = Icons.Rounded.CreditCard
    ),
)
