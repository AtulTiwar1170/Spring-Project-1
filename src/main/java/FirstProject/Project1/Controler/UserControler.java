package FirstProject.Project1.Controler;

import FirstProject.Project1.Entity.UserEntity;
import FirstProject.Project1.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserControler {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity newUser){
        try{
            userService.saveEntry(newUser);
            return new ResponseEntity<>(newUser, HttpStatusCode.valueOf(201));  //created
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400)); // Bad request
        }
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity updateUser, @PathVariable String username) {
        try{
            UserEntity user = userService.findByUsername(username);
            if(user != null){
                user.setUsername(updateUser.getUsername());
                user.setPassword(updateUser.getPassword());
                userService.saveEntry(user);
                return new ResponseEntity<>(HttpStatusCode.valueOf(201));
            }
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400)); //Bad request
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            List<UserEntity> all = userService.getAll();
            if(all != null && !all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/getUser/id/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable  ObjectId id) {
        try{
            Optional<UserEntity> byId = userService.findById(id);
            if(byId.isPresent()){
                return new ResponseEntity<>(byId.get(), HttpStatusCode.valueOf(200));
            }
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        } catch (Exception err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteUser/id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId id){
        try{
            userService.deleteById(id);
            return new ResponseEntity<>("Successfully deleted",HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400)); //Bad request
        }
    }


}
