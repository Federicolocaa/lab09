package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

    private static final String PRINT_BUTTON = "Print";
    private static final String HISTORY_BUTTON = "Show History";
    private static final String TITLE = "MVC-IO";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller;

    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField newField = new JTextField();
        canvas.add(newField, BorderLayout.NORTH);
        final JTextArea newArea = new JTextArea();
        canvas.add(newArea, BorderLayout.CENTER);
        final JButton print = new JButton(PRINT_BUTTON);
        final JButton history = new JButton(HISTORY_BUTTON);
        final JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        canvas.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(print);
        southPanel.add(history);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignore) {
                SimpleGUI.this.controller.setNextString(newField.getText());
                SimpleGUI.this.controller.currentString();
                newField.setText("");
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignore) {
                final List<String> history = SimpleGUI.this.controller.getHistoryString();

                for (final String s : history) {
                    newArea.append(s);                
                }
            }
        });

        frame.pack();
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    /**
     * @param args
     * ignored
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
