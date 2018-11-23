package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


/* ToDoListGUI.java requires no other files. */
public class ToDoListGUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addTaskString = "Add Task";
    private static final String removeTaskString = "Remove Task";
    private static final String removeAllTaskString = "Remove All Tasks";
    private JButton removeTaskButton;
    private JButton removeAllTaskButton;
    private JTextField taskName;
    private JLabel theLabel;
    private Icon[] icons;

    public ToDoListGUI() throws IOException {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // LEFT PANEL ToDoList
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        "Welcome to Ken's ToDoList Application!"),
                BorderFactory.createEmptyBorder(0,10,10,10)));

        listModel = new DefaultListModel();
        listModel.addElement("");

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(200);

        JScrollPane listScrollPane = new JScrollPane(list);

        //create addTask button
        JButton addTaskButton = new JButton(addTaskString);
        try {
            Image img = ImageIO.read(getClass().getResource("/images/diary.png"));
            addTaskButton.setIcon(new ImageIcon(img));
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
            Image img = ImageIO.read(getClass().getResource("/images/bin.png"));
            removeTaskButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //create removeAllTask button
        removeAllTaskButton = new JButton(removeAllTaskString);
        removeAllTaskButton.setActionCommand(removeAllTaskString);
        removeAllTaskButton.addActionListener(new RemoveAllTaskListener());
        removeAllTaskButton.setEnabled(false);
        try {
            Image img = ImageIO.read(getClass().getResource("/images/bin.png"));
            removeAllTaskButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        taskName = new JTextField(25);
        GhostText ghostText = new GhostText(taskName, "Please enter your task here...");
        taskName.addActionListener(addTaskListener);
        taskName.getDocument().addDocumentListener(addTaskListener);
        //String name = listModel.getElementAt(list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
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

        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        leftPanel.add(buttonPane, BorderLayout.PAGE_END);

        add(leftPanel);


        //RIGHT PANE showing images
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("E:/CPSC210/projectw1_team536/src/images/above-adventure-aerial-air.jpg").getImage().getScaledInstance(500, 445, Image.SCALE_DEFAULT));
        theLabel = new JLabel();
        theLabel.setIcon(imageIcon);
        theLabel.setVerticalAlignment(SwingConstants.CENTER);
        theLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Play the sound button"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        rightPanel.add(theLabel);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);


    }

    class RemoveTaskListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
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
            playSound("E:/CPSC210/projectw1_team536/src/sounds/Alesis-Fusion-Acoustic-Bass-C2.wav");
        }
    }

    class RemoveAllTaskListener implements  ActionListener{
        public void actionPerformed(ActionEvent e) {
            playSound("E:/CPSC210/projectw1_team536/src/sounds/gun sound.wav");
            listModel.removeAllElements();
            removeAllTaskButton.setEnabled(false);

        }
    }

    //This listener is shared by the text field and the hire button.
    class AddTaskListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;
        int counter = 0;

        public AddTaskListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
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
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(taskName.getText());

            //Reset the text field.
            taskName.requestFocusInWindow();
            taskName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
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

    //This method is required by ListSelectionListener.
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
    public void playSound(String soundName){
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("ToDoListGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ToDoListGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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
