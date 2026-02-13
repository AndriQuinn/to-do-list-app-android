import android.provider.ContactsContract
import androidx.room.*
import com.quinn.to_do_list.data.local.entity.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insert(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Update
    suspend fun updateTask(tasks: Tasks)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Tasks>>

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}