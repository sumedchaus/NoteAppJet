package com.cs.noteappjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.cs.noteappjet.data.NotesDataSource
import com.cs.noteappjet.model.Note
import com.cs.noteappjet.screen.NoteScreen
import com.cs.noteappjet.ui.theme.NoteAppJetTheme
import com.cs.noteappjet.viewmodel.NoteViewModel
import com.google.android.material.badge.ExperimentalBadgeUtils
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
    noteViewModel: NoteViewModel = NoteViewModel()
) {
    val noteList = noteViewModel.getAllNotes()

    NoteScreen(
        notes = noteList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = { noteViewModel.removeNote(it)}
    )
}
