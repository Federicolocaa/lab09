package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    
    /**
    *
    * @param nextString the next string to print
    */
    void setNextString(String nextString);

    /**
    *
    * @return the next string to print
    */
    String getNextString();

    /**
    *
    * @return the history of the strings as a List of String
    */    
    List<String> getHistoryString();

    /**
    *
    */    
    void currentString();
    
}
