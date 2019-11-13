package repository.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import repository.db.entity.RasporedEntity;

@Dao
public interface RasporedDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRasporedi(List<RasporedEntity> rasporedi);

    @Query("SELECT * FROM raspored")
    LiveData<List<RasporedEntity>> getAllRasporedi();





}
