package model;

import Exceptions.TooManyThingsToDoException;

import java.io.IOException;
import java.text.ParseException;

public interface Loadable {
    void load() throws IOException, ParseException, TooManyThingsToDoException;
}
