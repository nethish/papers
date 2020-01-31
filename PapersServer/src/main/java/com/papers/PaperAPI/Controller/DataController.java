package com.papers.PaperAPI.Controller;

import com.papers.PaperAPI.Crypto.Encrypt;
import com.papers.PaperAPI.DataManager.Manager;
import com.papers.PaperAPI.Exceptions.UserNotFoundException;
import com.papers.PaperAPI.Models.Paper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DataController {
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

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    public List<Paper> getPapers(@RequestParam("username") String username) throws IOException, UserNotFoundException {
        username = Encrypt.decrypt(username);
        if (!manager.isUser(username)) return new ArrayList();
        List<Paper> papers = manager.getPapersByUsername(username);
        return papers;
    }

    @RequestMapping(value = "/new", method = RequestMethod.PUT)
    public List<Paper> getNewPaper(@RequestParam("username") String username) throws UserNotFoundException, IOException {
        username = Encrypt.decrypt(username);
        if(!manager.isUser(username)) new ArrayList();
        manager.getNewPaper("", username);
        return manager.getPapersByUsername(username);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public List<Paper> deletePaper(@RequestParam("username") String username, @RequestParam("paperId") long paperId) throws IOException, UserNotFoundException {
        username = Encrypt.decrypt(username);
        if (manager.isUser(username)) {
            manager.delete(username, paperId);
            return manager.getPapersByUsername(username);
        }
        return new ArrayList<>();
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public List<Paper> savePaper(@RequestParam("username") String username, @RequestBody Paper paper) throws IOException, UserNotFoundException {
        username = Encrypt.decrypt(username);
        if (manager.isUser(username)){
            manager.savePaper(paper);
            return manager.getPapersByUsername(username);
        }
        return new ArrayList<>();

    }
}
