package FirstProject.Project1.Services;


import FirstProject.Project1.Entity.JournalEntry;
import FirstProject.Project1.Entity.UserEntity;
import FirstProject.Project1.Repositry.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    //creating Entry
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        UserEntity user = userService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    //update user
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    //get All Entries
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    //get entry by id
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String username){
        UserEntity user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepo.deleteById(id);
    }

}
