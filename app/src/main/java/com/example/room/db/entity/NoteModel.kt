package com.example.room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Моделка всегда должно быть Entity
@Entity(tableName = "Model")
data class NoteModel(
    var textTitle: String,
    var textDescription: String,
    var textData: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

