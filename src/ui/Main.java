package ui;
import Exceptions.TooManyThingsToDoException;
import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    //Initiate the ToDoList application
    public static void main(String[] args) throws ParseException, IOException, TooManyThingsToDoException {
        ToDoList t = new ToDoList();
    }
}