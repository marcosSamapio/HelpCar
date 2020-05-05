package br.com.helpcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.helpcar.db.CalledDAO;
import br.com.helpcar.db.CalledRoomDatabase;
import br.com.helpcar.model.Called;

public class CalledRepository {
    private CalledDAO calledDAO;
    private LiveData<List<Called>> calledList;

    public CalledRepository(Application application) {
        CalledRoomDatabase db = CalledRoomDatabase.getInstance(application);
        calledDAO = db.calledDAO();
        calledList = calledDAO.listCalleds();
    }

    public void createCalled(Called called) {
        calledDAO.createCalled(called);
    }

    public LiveData<List<Called>> listCalleds() {
        return calledDAO.listCalleds();
    }
}
