package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My Second Java Graphical Interface";
    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    /**
     * Builds a new GUI.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        final JPanel uppPanel = new JPanel();
        canvas.setLayout(new BorderLayout());
        uppPanel.setLayout(new BorderLayout());

        final JTextArea area = new JTextArea();
        final JTextField uppArea = new JTextField(controller.getPath());
        uppArea.setEditable(false);

        final JButton save = new JButton("Save");
        final JButton browser = new JButton("Browse...");

        canvas.add(uppPanel, BorderLayout.NORTH);
        canvas.add(area, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        uppPanel.add(uppArea, BorderLayout.CENTER);
        uppPanel.add(browser, BorderLayout.LINE_END);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Handler per il salvataggio
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controller.save(area.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // NOPMD: 
                }
            }
        });

        /*
         * Handler per il Browse
         */
        browser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                final JFileChooser fileChooser = new JFileChooser("Where to save");
                fileChooser.setSelectedFile(controller.getFile());

                final int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    final File newDest = fileChooser.getSelectedFile();
                    controller.setDestination(newDest);
                    uppArea.setText(newDest.getPath());
                } else if (result != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
