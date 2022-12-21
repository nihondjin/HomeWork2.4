package com.example.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.db.entity.NoteModel

// Dao это интерфейс которая управляет данными например(добавляьт, удалять, обновлять)
// это уже пульт база данных
@Dao
interface NoteDao {

    @Query("SELECT * FROM Model")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(recyclerModel: NoteModel)

    @Update
    fun upDateUser(model: NoteModel)
}















    //Метод для получение данных
             //  @Query("SELECT * FROM prostoModel")
            // fun getAll(): List<NoteModel>

    //Добавить один элемент
        //  @Insert
        //   fun insert(model: NoteModel)

    //Добавить список элементов
         //  @Insert
         // fun insertAll(list: List<NoteModel>)

    //Удаление одного элемента
         //  @Delete
         // fun delete(model: NoteModel)

    //Удаление всех данных
         //  @Query("DELETE FROM prostoModel")
         //fun deleteAll()
    // Для обновлении заметки
         // @Update
        // fun upDateUser(model: NoteModel)
