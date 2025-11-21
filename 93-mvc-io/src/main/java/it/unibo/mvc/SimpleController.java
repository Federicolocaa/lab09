package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> history = new ArrayList<>();
    private String nextString;

    public SimpleController(String nextString) {
        this.nextString = nextString;
    }

    @Override
    public void setNextString(final String nextStringtoPrint) {
        this.nextString = Objects.requireNonNull(nextStringtoPrint, "It's a null values, not accepted!");
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistoryString() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void currentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("No string set yet!");
        }
        history.add(this.nextString);
        System.out.println(this.nextString); //NOPMD
    }

}
