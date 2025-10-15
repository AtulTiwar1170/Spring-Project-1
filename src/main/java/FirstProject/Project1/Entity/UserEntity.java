package FirstProject.Project1.Entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity {
    @Id
    private ObjectId id;

    @Indexed(unique = true) //using this annotation username will be unique , also it will enhance searching
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef //this annotation is used to connect database ==> Collection ==> document ==> field to other Database Id,  and in MYSQL  (it becomes a foreign key of journalentry)
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
