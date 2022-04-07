package com.cs.noteappjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.cs.noteappjet.screen.NoteScreen
import com.cs.noteappjet.ui.theme.NoteAppJetTheme
import com.cs.noteappjet.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppJetTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val noteViewModel : NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun NotesApp(
    noteViewModel: NoteViewModel
) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = notesList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = { noteViewModel.removeNote(it)}
    )
}
