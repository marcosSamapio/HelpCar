package br.com.helpcar.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import br.com.helpcar.model.User;
import br.com.helpcar.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel( Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void createUser(User user) { userRepository.createUser(user); }

    public User getUser() {
        return userRepository.getUser();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
