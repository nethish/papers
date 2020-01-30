package com.papers.PaperAPI;

import com.papers.PaperAPI.DataManager.Manager;
import com.papers.PaperAPI.Exceptions.UserNotFoundException;
import com.papers.PaperAPI.Models.Paper;
import com.papers.PaperAPI.Models.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, UserNotFoundException {
        Manager m = Manager.InitializeAndReturn();
        m.registerUser("nethish", "password");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("F:\\Angular\\PaperAPI\\src\\main\\resources\\files\\manager"));
        os.writeObject(m);
    }
}
