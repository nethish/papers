package com.papers.PaperAPI.Authentication;

import com.papers.PaperAPI.Crypto.Encrypt;
import com.papers.PaperAPI.DataManager.Manager;
import com.papers.PaperAPI.Models.JsonRequest;
import com.papers.PaperAPI.Models.UserResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthentication {
    private static Manager manager;

    static {
        try {
            manager = Manager.getManager();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path="/login", produces = "application/json", method = RequestMethod.POST)
    public UserResponse login(@RequestBody JsonRequest req) throws IOException, ClassNotFoundException {
        String username = req.getUsername();
        String password = req.getPassword();
        if(!manager.verifyUser(username, password)) {
            return new UserResponse("");
        };
        return new UserResponse(Encrypt.encrypt(username));
    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public UserResponse register(@RequestBody JsonRequest req) throws IOException, ClassNotFoundException {
        String username = req.getUsername();
        String password = req.getPassword();
        if(manager.registerUser(username, password)) {
            new UserResponse(Encrypt.encrypt(username));
        }
        return new UserResponse("");
    }
}
