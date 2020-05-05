package br.com.helpcar.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.helpcar.repository.CalledRepository;
import br.com.helpcar.model.Called;

public class CalledViewModel extends AndroidViewModel {
    private CalledRepository calledRepository;
    private LiveData<List<Called>> calledList;

    public CalledViewModel( Application application) {
        super(application);
        calledRepository = new CalledRepository(application);
        calledList = calledRepository.listCalleds();
    }

    public LiveData<List<Called>> listCalleds() { return calledRepository.listCalleds(); }

    public void createCalled(Called called) {calledRepository.createCalled(called); }
}
