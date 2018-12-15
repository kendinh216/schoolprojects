// The frame work of this GUI is based on the ListDemo and ListDialog from Oracle Java Swing examples
// The code for adding background music is from stackoverflow answer: https://stackoverflow.com/questions/43110050/how-to-add-background-music
// The code for adding icon images is from stackoverflow answer: https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton


package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;



public class ToDoListGUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addTaskString = "Add Task";
    private static final String removeTaskString = "Remove Task";
    private static final String removeAllTaskString = "Remove All Tasks";
    private static final String playMusicString = "Play music";
    private static final String stopMusicString = "Stop music";
    private JButton removeTaskButton;
    private JButton removeAllTaskButton;
    private JButton playMusicButton;
    private JButton stopMusicButton;
    private JTextField taskName;
    private JLabel theLabel;
    private Clip clip;


    public ToDoListGUI() throws IOException {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // Creates Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Welcome to Ken's ToDoList Application!"),
                BorderFactory.createEmptyBorder(0,10,10,10)));

        listModel = new DefaultListModel();
        listModel.addElement("");

        //Create a list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(200);

        JScrollPane listScrollPane = new JScrollPane(list);

        //create addTask button
        JButton addTaskButton = new JButton(addTaskString);
        try {
            ImageIcon addIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/diary.png").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
            addTaskButton.setIcon(addIcon);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        AddTaskListener addTaskListener = new AddTaskListener(addTaskButton);
        addTaskButton.setActionCommand(addTaskString);
        addTaskButton.addActionListener(addTaskListener);
        addTaskButton.setEnabled(false);

        //create removeTask button
        removeTaskButton = new JButton(removeTaskString);
        removeTaskButton.setActionCommand(removeTaskString);
        removeTaskButton.addActionListener(new RemoveTaskListener());
        removeTaskButton.setEnabled(false);
        try {
            ImageIcon removeIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/bin.png").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
            removeTaskButton.setIcon(removeIcon);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //create removeAllTask button
        removeAllTaskButton = new JButton(removeAllTaskString);
        removeAllTaskButton.setActionCommand(removeAllTaskString);
        removeAllTaskButton.addActionListener(new RemoveAllTaskListener());
        removeAllTaskButton.setEnabled(false);
        try {
            ImageIcon removeIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/bin.png").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
            removeAllTaskButton.setIcon(removeIcon);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        taskName = new JTextField(22);
        GhostText ghostText = new GhostText(taskName, "Please enter your task here...");
        taskName.addActionListener(addTaskListener);
        taskName.getDocument().addDocumentListener(addTaskListener);

        //Create a button panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(taskName);
        buttonPane.add(addTaskButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(removeTaskButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(removeAllTaskButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Add button panel and list panel to the main left panel
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        leftPanel.add(buttonPane, BorderLayout.PAGE_END);

        add(leftPanel);


        //Right Panel showing images and play background music
        //PLAY music button
        playMusicButton = new JButton(playMusicString){};
        playMusicButton.setPreferredSize(new Dimension(140,40));
        try {
            ImageIcon playIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/play-button_318-42541.jpg").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
            playMusicButton.setIcon(playIcon);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        playMusicButton.setActionCommand(playMusicString);
        playMusicButton.addActionListener(new PlayMusicListener());
        playMusicButton.setEnabled(true);

        //STOP music button
        stopMusicButton = new JButton(stopMusicString);
        stopMusicButton.setPreferredSize(new Dimension(140,40));
        try {
            ImageIcon stopIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/stop.jpg").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
            stopMusicButton.setIcon(stopIcon);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        stopMusicButton.setActionCommand(stopMusicString);
        stopMusicButton.addActionListener(new StopMusicListener());
        stopMusicButton.setEnabled(false);

        //Creates right panel button pane
        JPanel rightPanelButtonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        rightPanelButtonPane.add(playMusicButton);
        rightPanelButtonPane.add(stopMusicButton);

        //set image
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/aerial-main-mall-800x253.jpg").getImage().getScaledInstance(900, 400, Image.SCALE_DEFAULT));
        theLabel = new JLabel();
        theLabel.setIcon(imageIcon);
        theLabel.setVerticalAlignment(JLabel.CENTER);
        theLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Play the sound button"),
                BorderFactory.createEmptyBorder(0, 10,10,10)));
        rightPanel.add(theLabel);
        rightPanel.add(rightPanelButtonPane);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);



    }
    // Actions to be performed by play music button
    class PlayMusicListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playSound("E:/CPSC210/projectw1_team536/src/sounds/Waltz (Brahms) in ochestra.wav");
            playMusicButton.setEnabled(false);
            stopMusicButton.setEnabled(true);
        }
    }
    //Actions to be performed by stop music button
    class StopMusicListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stopSound();
            playMusicButton.setEnabled(true);
            stopMusicButton.setEnabled(false);

        }
    }
    //Actions to be performed by remove task button
    class RemoveTaskListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeTaskButton.setEnabled(false);
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeTaskButton.setEnabled(false);

            }
            else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            playSound("E:/CPSC210/projectw1_team536/src/sounds/toaster_1.wav");
        }
    }
    //Actions to be performed by remove all tasks button
    class RemoveAllTaskListener implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            playSound("E:/CPSC210/projectw1_team536/src/sounds/stapler_1.wav");
            listModel.removeAllElements();
            removeAllTaskButton.setEnabled(false);

        }
    }
    // Actions to be performed by add task button
    class AddTaskListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        int counter = 0;

        public AddTaskListener(JButton button) {
            this.button = button;

        }

        public void actionPerformed(ActionEvent e) {
            String name = taskName.getText();
            playSound("E:/CPSC210/projectw1_team536/src/sounds/cashier sound.wav");
            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                taskName.requestFocusInWindow();
                taskName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }
            counter ++;
            listModel.insertElementAt(counter + ". " + taskName.getText(), index);

            //Reset the text field.
            taskName.requestFocusInWindow();
            taskName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }


        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }


        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //Set the states of the buttons when any button is pressed
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeAllTaskButton.setEnabled(false);
                removeTaskButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeAllTaskButton.setEnabled(true);
                removeTaskButton.setEnabled(true);
            }
        }
    }
    // Play music
    public void playSound(String soundName) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
    }
    // Stop music
    public void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }

    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("ToDoListGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ToDoListGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
