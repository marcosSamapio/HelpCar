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

    public List<User> getUsers() { return userRepository.getUsers(); }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public User verifyLogin(String email, String password) {
        List<User> users = getUsers();
        User user;
        int size = users.size();
        int i = 0;
        while(i < size) {
            user = users.get(i);
            if(email == user.getUserEmail() && password == user.getUserPassword()) {
                i = size + 1;
                return user;
            }
            i++;
        }
        return null;
    }
}
