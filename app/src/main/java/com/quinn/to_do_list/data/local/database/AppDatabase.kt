import androidx.room.Database
import androidx.room.RoomDatabase
import com.quinn.to_do_list.data.local.entity.Tasks


@Database(entities = [Tasks::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}