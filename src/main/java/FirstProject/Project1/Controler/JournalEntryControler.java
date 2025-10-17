package FirstProject.Project1.Controler;


import FirstProject.Project1.Entity.JournalEntry;
import FirstProject.Project1.Entity.UserEntity;
import FirstProject.Project1.Services.JournalEntryService;
import FirstProject.Project1.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControler {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/getAllEntry/{username}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String username){
        UserEntity user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createEntry/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry newEntry, @PathVariable String username) {
        try{

            journalEntryService.saveEntry(newEntry, username);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch(Exception err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEntry/id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        try{
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        } catch(Exception err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteEntry/id/{username}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username){
        try{
            journalEntryService.deleteById(id, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEntry/id/{username}/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id,
                                             @PathVariable String username,
                                             @RequestBody JournalEntry updatedEntry){
        try{
            JournalEntry oldJournalEntry = journalEntryService.findById(id).orElse(null);
            if(oldJournalEntry != null){
                oldJournalEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle() : oldJournalEntry.getTitle());
                oldJournalEntry.setContent((updatedEntry.getContent()) != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent() : oldJournalEntry.getContent());
                journalEntryService.saveEntry(oldJournalEntry);
                return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch(Exception err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
