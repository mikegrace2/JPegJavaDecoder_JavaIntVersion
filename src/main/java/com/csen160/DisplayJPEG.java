package com.csen160;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class DisplayJPEG extends JFrame {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(new File("C:\\Users\\mschi\\Documents\\JPegDecode\\"));
        chooser.setDialogTitle("Select a JPEG image");
        chooser.setFileFilter(new FileNameExtensionFilter("JPEG images", "jpg", "jpeg"));

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();
        JFrame frame = new JFrame("JPEG Image");

        ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath());
        JLabel label = new JLabel(image);

        frame.add(label);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
