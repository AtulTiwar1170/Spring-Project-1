package FirstProject.Project1.Services;

import FirstProject.Project1.Entity.UserEntity;
import FirstProject.Project1.Repositry.UserRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepositry userRepositry;

    public void saveEntry(UserEntity userEntity){
        userRepositry.save(userEntity);
    }

    public List<UserEntity> getAll(){
        return userRepositry.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepositry.findById(id);
    }

    public UserEntity findByUsername(String username) {
        return userRepositry.findByUsername(username);
    }
    public void deleteById(ObjectId id) {
        userRepositry.deleteById(id);
    }
}
