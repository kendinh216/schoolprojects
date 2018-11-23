package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Slideshow extends JFrame {

    private JLabel slidesLabel = new JLabel();
    private Icon[] icons;
    private int currentSlide = -1;

    public Slideshow() {
        try {
            // Personally, I'd use File#listFiles to list all the
            // images in a directory, but that might be consider
            // using our initiative...
            icons = new Icon[]{
                    new ImageIcon(ImageIO.read(new File("E:/CPSC210/projectw1_team536/src/images/above-adventure-aerial-air.jpg"))),
                    new ImageIcon(ImageIO.read(new File("E:/CPSC210/projectw1_team536/src/images/aerial-main-mall-800x253.jpg"))),
                    new ImageIcon(ImageIO.read(new File("E:/CPSC210/projectw1_team536/src/images/aerial_moa_panorama-1920x700.jpg"))),
            };
            slidesLabel.setVerticalAlignment(JLabel.CENTER);
            slidesLabel.setHorizontalAlignment(JLabel.CENTER);
            setLayout(new BorderLayout());
            add(slidesLabel, BorderLayout.CENTER);
            slidesLabel.addMouseListener(new ClickListener());
            nextSlide();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void nextSlide() {
        if (currentSlide < icons.length - 1) {
            currentSlide++;
            slidesLabel.setIcon(icons[currentSlide]);
        }
    }

////    public static void main(String args[]) {
////        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    ex.printStackTrace();
//                }
//
//                Slideshow slideshow = new Slideshow();
//                slideshow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                slideshow.pack();
//                slideshow.setLocationRelativeTo(null);
//                slideshow.setVisible(true);
//            }
//        });
//    }

    public class ClickListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            nextSlide();
        }
    }

}