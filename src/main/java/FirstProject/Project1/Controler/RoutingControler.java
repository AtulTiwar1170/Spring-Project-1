package FirstProject.Project1.Controler;


import FirstProject.Project1.entity.UserData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoutingControler {


    private Map<Long, UserData> usersData = new HashMap<>();

    //Register user
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody UserData user){
        usersData.put(user.getId(), user);
        return true;
    }

    //login user
    @PostMapping("/login")
    public String login(){
        return "login route";
    }


    //get All user
    @GetMapping("/getAllUsers")
    public List<UserData> getAlluser(){
        return new ArrayList<>(usersData.values());
    }

    //Search user by id and using params or dynamin routing
    @GetMapping("/getUserById/{id}")
    public UserData getUserById(@PathVariable long id) {
        return usersData.get(id);
    }


    //update user
    @PutMapping("/updateUser/{id}")
    public UserData updateUser(@PathVariable long id, @RequestBody UserData user) {
        return usersData.put(id, user);
    }


    //Delete User
    @DeleteMapping("/deleteUser/{id}")
    public UserData deleteUser(@PathVariable long id) {
        return usersData.remove(id);
    }

}
