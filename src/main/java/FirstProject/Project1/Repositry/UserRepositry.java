package FirstProject.Project1.Repositry;


import FirstProject.Project1.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositry extends MongoRepository<UserEntity, ObjectId> {
}
