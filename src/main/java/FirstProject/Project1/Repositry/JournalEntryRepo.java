package FirstProject.Project1.Repositry;

import FirstProject.Project1.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


//mongodb integeration
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
