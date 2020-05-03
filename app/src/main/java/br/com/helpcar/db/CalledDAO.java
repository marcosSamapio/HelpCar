package br.com.helpcar.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.helpcar.model.Called;

@Dao
public interface CalledDAO {
    @Insert
    void createCalled(Called called);

    @Query("SELECT * FROM Called ORDER BY calledId DESC")
    LiveData<List<Called>> listCalleds();
}
