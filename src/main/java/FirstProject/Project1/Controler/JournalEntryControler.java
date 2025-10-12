package FirstProject.Project1.Controler;


import FirstProject.Project1.Entity.JournalEntry;
import FirstProject.Project1.Services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControler {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllEntry")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping("/createEntry")
    public boolean createEntry(@RequestBody JournalEntry newEntry) {
        newEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(newEntry);
        return true;
    }

    @GetMapping("/getEntry/id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("/deleteEntry/id/{id}")
    public boolean deleteEntryById(@PathVariable ObjectId id){
        journalEntryService.deleteById(id);
        return true;
    }

    @PutMapping("/updateEntry/id/{id}")
    public boolean updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry updatedEntry){
        JournalEntry oldJournalEntry = journalEntryService.findById(id).orElse(null);
        if(oldJournalEntry != null){
            oldJournalEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent((updatedEntry.getContent()) != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent() : oldJournalEntry.getContent());
        }
        journalEntryService.saveEntry(oldJournalEntry);
        return true;
    }

}
