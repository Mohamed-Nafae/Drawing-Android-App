package com.bm.drawappbm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bm.drawappbm.ui.theme.DrawAppBMTheme
import com.bm.drawappbm.ui.theme.thumbColor

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawAppBMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = viewModel<DrawingViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        DrawingCanvas(
                            paths = state.paths,
                            currentPath = state.currentPath,
                            thickness = state.thickness,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            onAction = viewModel::onAction
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = Modifier
                                    .weight(0.115f) // Give it some space
                                    .padding(8.dp)
                                    .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                                    .size(30.dp),
                                contentAlignment = Alignment.Center
                            ){
                                // The actual preview circle
                                val thicknessInDp = with(LocalDensity.current) { state.thickness.toDp() }
                                Box(
                                    modifier = Modifier
                                        .size(thicknessInDp)
                                        .background(
                                            color = state.selectedColor, // Use the currently selected color
                                            shape = CircleShape
                                        )
                                )
                            }
                            Slider(
                                modifier = Modifier.weight(0.9f),
                                value = state.thickness,
                                valueRange = 10f..50f,
                                steps = 5,
                                onValueChange = {
                                    viewModel.onAction(DrawingAction.OnChangingThickness(it))
                                },
                                thumb = {
                                    Box(
                                        modifier = Modifier
                                            .padding(0.dp)
                                            .width(8.dp).height(30.dp)
                                            .background(color = thumbColor, shape= RoundedCornerShape(50))
                                    )
                                }
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            allColor.fastForEach{ color ->
                                val isSelected = state.selectedColor == color
                                Box(
                                    Modifier
                                        .graphicsLayer {
                                            val scale = if (isSelected) 1.2f else 1f
                                            scaleX = scale
                                            scaleY = scale
                                        }
                                        .size(35.dp)
                                        .background(color = color, shape = CircleShape)
                                        .border(width = 2.dp, color = if(isSelected) Color.Black else Color.Transparent, shape = CircleShape)
                                        .clickable(
                                            enabled = true,
                                            onClick = {
                                                viewModel.onAction(DrawingAction.OnSelectColor(color))
                                            }
                                        )
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.onAction(DrawingAction.OnUndoClick)
                                },
                                modifier = Modifier.padding(20.dp).offset(x= -(20.dp))
                            ) {
                                Icon(Icons.Rounded.Undo, contentDescription = "Undo", tint = thumbColor)
                            }
                            Button(
                                onClick = {
                                    viewModel.onAction(DrawingAction.OnClearCanvasClick)
                                },
                                modifier = Modifier.fillMaxWidth(0.5f).offset(x = -(30.dp))
                            ) {
                                Text(text = "Clear Painting")
                            }
                        }
                    }
                }
            }
        }
    }
}
