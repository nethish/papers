package com.papers.PaperAPI.DataManager;

import com.papers.PaperAPI.Exceptions.UserNotFoundException;
import com.papers.PaperAPI.Models.Paper;
import com.papers.PaperAPI.Models.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Serializable {
    private List<User> users;
    private List<Paper> papers;
    private long userCount = 0;
    private long paperCount = 0;
    private static Manager manager = null;

    public Manager() {
    }

    public static Manager getManager() throws IOException, ClassNotFoundException {
        if (manager == null) loadManager();
        return manager;
    }

    public static Manager InitializeAndReturn() {
        Manager m = new Manager();
        m.users = new ArrayList<>();
        m.papers = new ArrayList<>();
        return m;
    }

    public Boolean verifyUser(String username, String password) {
        for(User user: users) if (user.getUsername().equals(username) && user.getPassword().equals(password))
            return true;
        return false;
    }

    public Boolean isUser(String username) {
        for(User user: users) if(user.getUsername().equals(username))
            return true;
        return false;
    }

    public static void loadManager() throws IOException, ClassNotFoundException {
        Resource resource = new ClassPathResource("/files/manager");
        InputStream in = resource.getInputStream();
        ObjectInputStream objIn = new ObjectInputStream(in);
        manager = (Manager) objIn.readObject();
        if (manager == null) manager = new Manager();
    }

    public User getNewUser(String username, String password) {
        User u = new User(username, password);
        userCount++;
        u.setUserId(userCount);
        return u;
    }

    public Paper getNewPaper(String title, long userId) {
        paperCount++;
        Paper p = new Paper();
        p.setTitle(title);
        p.setPaperId(paperCount);
        p.setUserId(userId);
        this.papers.add(p);
        return p;
    }

    public List<Paper> getPapersByUserId(long userId) throws IOException {
        List<Paper> userPapers = new ArrayList<Paper>();
        for(Paper paper: papers) {
            if (paper.getUserId() == userId) userPapers.add(paper);
        }
        return userPapers;
    }

    public List<Paper> getPapersByUsername(String username) throws UserNotFoundException, IOException {
        long id = getUserIdByName(username);
        if (id == -1) return null;
        return getPapersByUserId(id);
    }

    public User getUserByUsername(String username) throws IOException, ClassNotFoundException {
        for(User u: this.users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public long getUserIdByName(String username) throws UserNotFoundException {
        for(User u: users) {
            if (u.getUsername().equals(username)) return u.getUserId();
        }
        throw new UserNotFoundException();
    }

    public Paper getNewPaper(String title, String username) throws UserNotFoundException, IOException {
        long userId = getUserIdByName(username);
        return getNewPaper(title, userId);
    }

    public Boolean savePaper(Paper p) {
        for(Paper paper: papers) if (paper.getPaperId() == p.getPaperId()) {
            paper.setTitle(p.getTitle());
            paper.setContent(p.getContent());
        }
        return true;
    }

    public Boolean registerUser(String username, String password) throws IOException, ClassNotFoundException {
        if (this.getUserByUsername(username) != null) return false;
        this.users.add(getNewUser(username, password));
        return true;
    }

    public Boolean delete(long paperId) {
        papers.removeIf(paper -> {
            return (paper.getPaperId() == paperId);
        });
        return true;
    }

    public Boolean delete(String username, long paperId) {
        if (!isUser(username)) return false;
        return delete(paperId);
    }
}
