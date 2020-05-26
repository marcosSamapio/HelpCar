package br.com.helpcar.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import br.com.helpcar.model.User;
import br.com.helpcar.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel( Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User getUser(int userId) { return userRepository.getUser(userId); }

    public User verifyLogin(String userEmail, String userPassword) {
        User user = userRepository.getUser(userEmail, userPassword);
        if (user != null) {
            if(userEmail.equals(user.getUserEmail()) && userPassword.equals(user.getUserPassword())) {
                return user;
            } else return null;
        } else return null;

    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
