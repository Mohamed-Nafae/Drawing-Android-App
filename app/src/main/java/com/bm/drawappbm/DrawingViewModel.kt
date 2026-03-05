package com.bm.drawappbm

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class DrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
    val thickness: Float = 10f
)

val allColor = listOf(
    Color.Black,
    Color.Red,
    Color.Blue,
    Color.Green,
    Color.Yellow,
    Color.Gray,
    Color.Magenta,
    Color.Cyan
)
data class PathData(
    val id: String,
    val path: List<Offset>,
    val color: Color,
    val thickness: Float
)

sealed interface DrawingAction{
    data object OnNewPathStart: DrawingAction
    data class OnDraw(val offset: Offset): DrawingAction
    data object OnPathEnd: DrawingAction
    data class OnSelectColor(val color: Color): DrawingAction
    data object OnClearCanvasClick: DrawingAction
    data class OnChangingThickness(val thickness: Float): DrawingAction
    data object OnUndoClick: DrawingAction
}

class DrawingViewModel: ViewModel(){
    private val _state = MutableStateFlow(DrawingState())
    val state = _state.asStateFlow()

    fun onAction(action: DrawingAction){
        when(action){
            DrawingAction.OnClearCanvasClick -> onClearCanvasClick()
            is DrawingAction.OnDraw -> onDraw(action.offset)
            DrawingAction.OnNewPathStart -> onNewPathStart()
            DrawingAction.OnPathEnd -> onPathEnd()
            is DrawingAction.OnSelectColor -> onSelectColor(action.color)
            is DrawingAction.OnChangingThickness -> onChangingThickness(action.thickness)
            is DrawingAction.OnUndoClick -> onUndoClick()

        }
    }

    private fun onUndoClick() {
        if(_state.value.paths.isNotEmpty())
            _state.update {
                it.copy(
                    paths = it.paths.dropLast(1)
                )
            }
    }

    private fun onChangingThickness(thickness: Float) {
        _state.update {
            it.copy(
                thickness = thickness
            )
        }
    }

    private fun onSelectColor(color: Color) {
        _state.update {
            it.copy(
                selectedColor = color
            )
        }
    }

    private fun onPathEnd() {
        val currentPathData = _state.value.currentPath ?: return
        _state.update {
            it.copy(
                paths = it.paths + currentPathData,
                currentPath = null
            )
        }
    }

    private fun onNewPathStart() {
        _state.update {
            it.copy(
                currentPath = PathData(
                    id = System.currentTimeMillis().toString(),
                    path = emptyList(),
                    color = it.selectedColor,
                    thickness = it.thickness
                )
            )
        }
    }

    private fun onDraw(offset: Offset) {
        val currentPathData = _state.value.currentPath ?: return
        _state.update {
            it.copy(
                currentPath = currentPathData.copy(
                    path = currentPathData.path + offset
                )
            )
        }
    }

    private fun onClearCanvasClick() {
        _state.update {
            it.copy(
                paths = emptyList(),
                currentPath = null
            )
        }
    }
}