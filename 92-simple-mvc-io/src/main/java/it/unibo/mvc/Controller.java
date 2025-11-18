package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    private File currentFile;

    /**
     * Builds a new Controller.
     */
    public Controller() {
        final String userHome = System.getProperty("user.home");
        final String separator = System.getProperty("file.separator"); 
        final String path = userHome + separator + "output.txt";
        this.currentFile = new File(path);
    }

    /**
     * Sets the current destination file.
     *
     * @param file the file where to write
     */
    public void setDestination(final File file) {
        this.currentFile = file;
    }

    /**
     * Gets the current file.
     *
     * @return the current file
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * Gets the path of the current file.
     *
     * @return the path of the current file
     */
    public String getPath() {
        return this.currentFile.getPath();
    }

    /**
     * Saves the content to the file.
     *
     * @param content the content to save
     * @throws IOException if an error occurs
     */
    public void save(final String content) throws IOException {
        try (PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.print(content);
        }
    }
}
