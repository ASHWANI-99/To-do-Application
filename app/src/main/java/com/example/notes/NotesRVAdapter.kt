package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listener : INoteRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    //getting data in array list pass
    val allNotes = ArrayList<Note>()



    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteView = itemView.findViewById<ImageView >(R.id.delete_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteView.setOnClickListener{
            listener.onItemclicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList : List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INoteRVAdapter {
    fun onItemclicked (note: Note)
    
}