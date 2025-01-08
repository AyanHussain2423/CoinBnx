package com.example.coinbnx.Component

import android.graphics.LinearGradient
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.onGloballyPositioned
import kotlin.math.floor

@Composable
fun SparklineChart(
    modifier: Modifier = Modifier,
    sparklineData: List<Float>,
    lineColor: ComposeColor = MaterialTheme.colorScheme.primary,
    backgroundColor: ComposeColor = ComposeColor.Transparent,
    change : String
) {
    var chartWidth by remember { mutableStateOf(0f) }
    var chartHeight by remember { mutableStateOf(0f) }

    val lineColor = MaterialTheme.colorScheme.onBackground.copy(0.7f)
    val maxValue = sparklineData.maxOrNull() ?: 0f
    val minValue = sparklineData.minOrNull() ?: 0f

    // Scaling factors for X and Y axes
    val scaleX = chartWidth / (sparklineData.size - 1).toFloat()
    val scaleY = chartHeight / ((maxValue - minValue).takeIf { it != 0f } ?: 1f)

    val changeValue = change.toFloatOrNull() ?: 0f

    // Define the color based on whether the change is positive or negative
    val changeColor = if (changeValue > 0) Color.Green else if (changeValue < 0) Color.Red else Color.Gray

    Column(
        modifier = modifier
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // Add top padding

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .onGloballyPositioned { coordinates ->
                chartWidth = coordinates.size.width.toFloat()
                chartHeight = coordinates.size.height.toFloat()
            }
            .padding(start = 10.dp, end = 20.dp) // Add padding on sides
        ) {
            // Draw background
            drawRect(color = backgroundColor)

            // Draw the vertical Y-axis (left side)
            drawLine(
                color = lineColor.copy(alpha = 0.4f),
                start = Offset(0f, 0f),  // Start at top
                end = Offset(0f, chartHeight),  // End at bottom
                strokeWidth = 1.5f
            )

            // Draw the horizontal X-axis (bottom)
            drawLine(
                color = lineColor.copy(alpha = 0.4f),
                start = Offset(0f, chartHeight),  // Start at the bottom (left side)
                end = Offset(chartWidth, chartHeight),  // End at the bottom (right side)
                strokeWidth = 1.5f
            )

            // Draw Y-axis labels (price)
            val priceLabels = listOf(maxValue, (maxValue + minValue) / 2, minValue) // Show 3 labels: max, mid, min
            priceLabels.forEachIndexed { index, label ->
                val yPosition = chartHeight - (label - minValue) * scaleY
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        String.format("%.2f", label),
                        10f,
                        yPosition,  // Y position
                        Paint().apply {
                            color = lineColor.toArgb()
                            textSize = 18f
                            isAntiAlias = true
                            textAlign = Paint.Align.RIGHT
                        }
                    )
                }
            }

            // Draw horizontal grid lines for better readability
            val gridLines = listOf(maxValue, (maxValue + minValue) / 2, minValue)
            gridLines.forEach { gridValue ->
                val yPosition = chartHeight - (gridValue - minValue) * scaleY
                drawLine(
                    color = lineColor.copy(alpha = 0.2f),
                    start = Offset(0f, yPosition),
                    end = Offset(chartWidth, yPosition),
                    strokeWidth = 1f
                )
            }

            // Create a path for the line chart with curves
            val path = Path().apply {
                moveTo(0f, chartHeight - (sparklineData[0] - minValue) * scaleY) // Start at the first point
                sparklineData.forEachIndexed { index, value ->
                    if (index > 0) {
                        val prevX = (index - 1) * scaleX
                        val prevY = chartHeight - (sparklineData[index - 1] - minValue) * scaleY
                        val currentX = index * scaleX
                        val currentY = chartHeight - (value - minValue) * scaleY

                        // Use quadraticBezierTo for smoothing the curve
                        val midX = (prevX + currentX) / 2
                        val midY = (prevY + currentY) / 2
                        quadraticBezierTo(midX, midY, currentX, currentY)
                    }
                }
            }

            // Draw the sparkline (line) on top of the filled area
            drawPath(path = path, color = changeColor, style = Stroke(width = 3f))

        }

        Spacer(modifier = Modifier.height(16.dp)) // Add bottom padding
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSparklineChart() {
    val sparkline = listOf(
        97762.16129950038f, 97816.1052557557f, 97902.03153114325f, 97895.35266910978f,
        97992.72324831097f, 97875.08204274035f, 97743.72849750044f, 97855.651516651f,
        98059.02628981539f, 98157.14591456852f, 98434.02198939855f, 98536.9600214094f,
        98388.53637357875f, 98284.2637547189f, 98284.63299646918f, 98213.41300985057f,
        98191.8705849661f, 98182.26958204537f, 98384.76262258574f, 98384.56164134748f,
        98398.48262387587f, 98399.11014973906f, 97953.12277172928f, 97872.26467094982f
    )

    SparklineChart(
        sparklineData = sparkline,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        lineColor = ComposeColor(0xFF64B5F6),
        change = "64B5F6"
    )
}
