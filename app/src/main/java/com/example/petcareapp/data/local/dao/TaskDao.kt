package com.example.petcareapp.data.local.dao


import androidx.room.*
import com.example.petcareapp.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE pet = :pet ORDER BY dueDate")
    fun getTasksForPet(pet: String): Flow<List<TaskEntity>>

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: String)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}
