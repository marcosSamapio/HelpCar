package br.com.helpcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.helpcar.db.CalledDAO;
import br.com.helpcar.db.CalledRoomDatabase;
import br.com.helpcar.model.Called;

public class CalledRepository {
    private CalledDAO calledDAO;

    public CalledRepository(Application application) {
        CalledRoomDatabase db = CalledRoomDatabase.getInstance(application);
        calledDAO = db.calledDAO();
    }

    public void createCalled(Called called) {
        calledDAO.createCalled(called);
    }

    public LiveData<List<Called>> listCalleds(int userId) {
        return calledDAO.listCalleds(userId);
    }
}
