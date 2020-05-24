package br.com.helpcar.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import br.com.helpcar.db.CalledRoomDatabase;
import br.com.helpcar.db.UserDAO;
import br.com.helpcar.model.User;

public class UserRepository {
    private UserDAO userDao;

    public UserRepository(Application application) {
        CalledRoomDatabase db = CalledRoomDatabase.getInstance(application);
        userDao = db.userDAO();
    }

    public void createUser(User user) {
        userDao.createUser(user);
        Log.i("usuario", ""+user.getUserName());
    }

    public User getUser(int userId) { return userDao.getUser(userId); }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
