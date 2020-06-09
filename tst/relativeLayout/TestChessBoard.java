package relativeLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TestChessBoard {

    public static void main(String[] args) {
        new TestChessBoard();
    }

    public TestChessBoard() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ChessBoardPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ChessBoardPane extends JPanel {

        public ChessBoardPane() {
            int index = 0;
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Color color = index % 2 == 0 ? Color.BLACK : Color.WHITE;
                    gbc.gridx = col;
                    gbc.gridy = row;
                    add(new Cell(color), gbc);
                    index++;
                }
                index++;
            }
        }

    }

    public class Cell extends JButton {

        public Cell(Color background) {

            setContentAreaFilled(false);
            setBorderPainted(false);
            setBackground(background);
            setOpaque(true);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(25, 25);
        }

    }

}