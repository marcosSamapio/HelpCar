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
    void insert(Called called);

    @Query("SELECT * FROM Called")
    LiveData<List<Called>> listCalleds();
}
