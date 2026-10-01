package com.csen160;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class DisplayJPEG extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(new File("C:\\Users\\mschi\\Documents\\JPegDecode\\"));
        chooser.setDialogTitle("Select a JPEG image");
        chooser.setFileFilter(new FileNameExtensionFilter("JPEG images", "jpg", "jpeg"));

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = chooser.getSelectedFile();
        JFrame frame = new JFrame("JPEG Image");

        ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath()); // This one line does the whole JPeg decoding for you, no need to implement it yourself.
        frame.add(new ScaledImagePanel(image.getImage()));

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class ScaledImagePanel extends JPanel {
        private final Image image;

        private ScaledImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            int imageWidth = image.getWidth(this);
            int imageHeight = image.getHeight(this);
            if (imageWidth <= 0 || imageHeight <= 0) {
                return;
            }

            double scale = Math.min(
                    (double) getWidth() / imageWidth,
                    (double) getHeight() / imageHeight
            );
            int scaledWidth = (int) Math.round(imageWidth * scale);
            int scaledHeight = (int) Math.round(imageHeight * scale);
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;

            Graphics2D graphics2D = (Graphics2D) graphics.create();
            try {
                graphics2D.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR
                );
                graphics2D.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            } finally {
                graphics2D.dispose();
            }
        }
    }
}
