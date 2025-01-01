package com.example.coinbnx.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.LibraryBooks
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.coinbnx.ui.theme.Blur

@Composable
fun BottomBar(modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .blur(20.dp)  // Applying blur effect
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f))  // Semi-transparent dark overlay for better contrast
            .clip(RoundedCornerShape(10.dp))
    )

    // Content Layer (Text)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    )
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        bottomBarItems.forEach {
            if (it.isSelected) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clip(RoundedCornerShape(14.dp))
                        .background(Blur),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            } else {
                Icon(
                    imageVector = it.icon,
                    contentDescription = it.title,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}


data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean
)

val bottomBarItems = listOf(
    BottomBarItem(
        title = "Home",
        icon = Icons.Rounded.Home,
        isSelected = false
    ),
    BottomBarItem(
        title = "Account",
        icon = Icons.Rounded.AccountBox,
        isSelected = true
    ),
    BottomBarItem(
        title = "Analytics",
        icon = Icons.AutoMirrored.Rounded.LibraryBooks,
        isSelected = false
    ),
    BottomBarItem(
        title = "Settings",
        icon = Icons.Rounded.Settings,
        isSelected = false
    ),
)