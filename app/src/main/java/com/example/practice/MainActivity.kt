package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practice.ui.theme.PracticeTheme
import com.example.practice.ui.theme.royalThemes
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                ColorSchemeDemo()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSchemeDemo() {

    var currentTheme by remember {
        mutableStateOf(royalThemes.keys.first())
    }

    val colorScheme = if (isSystemInDarkTheme()) royalThemes[currentTheme]!!.second else royalThemes[currentTheme]!!.first

    val colorPairs = listOf(
        Triple("Primary", colorScheme.primary, colorScheme.onPrimary),
        Triple("Secondary", colorScheme.secondary, colorScheme.onSecondary),
        Triple("Tertiary", colorScheme.tertiary, colorScheme.onTertiary),
        Triple("Primary Container", colorScheme.primaryContainer, colorScheme.onPrimaryContainer),
        Triple("Secondary Container", colorScheme.secondaryContainer, colorScheme.onSecondaryContainer),
        Triple("Tertiary Container", colorScheme.tertiaryContainer, colorScheme.onTertiaryContainer),
        Triple("Background", colorScheme.background, colorScheme.onBackground),
        Triple("Surface", colorScheme.surface, colorScheme.onSurface),
        Triple("Surface Variant", colorScheme.surfaceVariant, colorScheme.onSurfaceVariant),
        Triple("Error", colorScheme.error, colorScheme.onError),
        Triple("Error Container", colorScheme.errorContainer, colorScheme.onErrorContainer),
        Triple("Inverse Surface", colorScheme.inverseSurface, colorScheme.inverseOnSurface),
        Triple("Outline", colorScheme.outline, Color.Black),  // Text in black for visibility
        Triple("Outline Variant", colorScheme.outlineVariant, Color.Black),
        Triple("Scrim", colorScheme.scrim, Color.White),  // Scrim usually overlays UI, so using white text
        Triple("Surface Bright", colorScheme.surfaceBright, colorScheme.onSurface),
        Triple("Surface Dim", colorScheme.surfaceDim, colorScheme.onSurface),
        Triple("Surface Container", colorScheme.surfaceContainer, colorScheme.onSurface),
        Triple("Surface Container High", colorScheme.surfaceContainerHigh, colorScheme.onSurface),
        Triple("Surface Container Highest", colorScheme.surfaceContainerHighest, colorScheme.onSurface),
        Triple("Surface Container Low", colorScheme.surfaceContainerLow, colorScheme.onSurface),
        Triple("Surface Container Lowest", colorScheme.surfaceContainerLowest, colorScheme.onSurface),
    )

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.background(colorScheme.background)
    ) {
        Spacer(modifier = Modifier.statusBarsPadding().height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = isExpanded,
                onExpandedChange = {
                    isExpanded = it
                }
            ) {
                Row(
                    modifier = Modifier.menuAnchor(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentTheme,
                        color = colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {
                    isExpanded = false
                }) {
                    royalThemes.keys.forEach { theme ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = theme,
                                    color = colorScheme.onBackground
                                )
                            }, onClick = {
                                currentTheme = theme
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .weight(1f)
                .background(colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            items(colorPairs.size) { index ->
                val item = colorPairs[index]
                ColorCard(item.first, item.second, item.third)
            }
        }
    }
}

@Composable
fun ColorCard(name: String, backgroundColor: Color, textColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = name, color = textColor, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun PreviewColorSchemeDemo() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        ColorSchemeDemo()
    }
}
