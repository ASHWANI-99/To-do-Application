package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteRVAdapter {

    lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adappter = NotesRVAdapter(this,this)
        recyclerView.adapter = adappter


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        // observing live data
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adappter.updateList(it)
            }

        })
    }

    override fun onItemclicked(note: Note) {
        viewModel.deleteNOte(note)
        Toast.makeText(this,"${note.text} Deleted" , Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = inputt.text.toString()
        if( noteText.isNotEmpty())
        {
             viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"${noteText} inserted" , Toast.LENGTH_SHORT).show()
        }

    }
}