package com.cs.noteappjet.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs.noteappjet.components.NoteButton
import com.cs.noteappjet.components.NoteInputText
import com.cs.noteappjet.data.NotesDataSource
import com.cs.noteappjet.model.Note
import com.cs.noteappjet.utils.formatDate
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Jet Notes App")
            }, actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon"
                )
            },
            )
        }) {
        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    modifier = Modifier.padding(8.dp),
                    text = title,
                    label = "title",
                    onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) title = it
                    }
                )
                NoteInputText(
                    modifier = Modifier.padding(8.dp),
                    text = description,
                    label = "Add a Note",
                    onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) description = it
                    }
                )

                NoteButton(
                    modifier = Modifier.padding(8.dp),
                    text = "Save",
                    onClick = {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            // save to note
                                onAddNote(
                                    Note(title = title,
                                    description = description)
                                )
                            title = ""
                            description = ""
                            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn{
                items(notes){note ->
                    NoteRow(note = note,
                        onNoteClicked = {
                            onRemoveNote(note)
                    })
                }
            }
        }
    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note:Note,
    onNoteClicked: (Note) -> Unit) {

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color.LightGray,
        elevation = 6.dp,
        
    ) {
        Column(modifier = Modifier
            .clickable { onNoteClicked(note)}
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption)
        }
        
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}