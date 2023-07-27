package edu.campusnum.visualsort.frames;


import edu.campusnum.visualsort.model.SortController;
import edu.campusnum.visualsort.model.SortState;
import edu.campusnum.visualsort.sort.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Visualiser {
    public static String length;
    private JFrame frame;

    private int sleepTime = 100;

    public Visualiser() {
        JPanel panel;
        JButton q, w, e, r, t, y, sleepBtn;
        frame = new JFrame();
        frame.setSize(510, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        q = new JButton("Bubble");
        w = new JButton("Selection");
        e = new JButton("Insertion");
        r = new JButton("Quick");
        t = new JButton("Merge");
        y = new JButton("Heap");
        sleepBtn = new JButton("Sleep Time");

        q.setBounds(100, 50, 100, 50);
        w.setBounds(100, 150, 100, 50);
        e.setBounds(300, 50, 100, 50);
        r.setBounds(300, 150, 100, 50);
        t.setBounds(100, 250, 100, 50);
        sleepBtn.setBounds(300, 250, 100, 50);

        q.addActionListener(v -> {
            SortAlgorithm algo = new BubbleSort();
            createVisualizer(algo);
        });

        w.addActionListener(v -> {
            SortAlgorithm algo = new SelectionSort();
            createVisualizer(algo);
        });

        e.addActionListener(v -> {
            SortAlgorithm algo = new InsertionSort();
            createVisualizer(algo);
        });

        r.addActionListener(v -> {
            SortAlgorithm algo = new QuickSort();
            createVisualizer(algo);
        });

        t.addActionListener(v -> {
            SortAlgorithm algo = new MergeSort();
            createVisualizer(algo);
        });

        sleepBtn.addActionListener(v -> {
            String sleepTime = JOptionPane.showInputDialog(frame, "Enter number number of millisecond to wait [1 to 1000]");
            try {
                while (Integer.parseInt(sleepTime) > 1000 || Integer.parseInt(sleepTime) <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please choose the number in the given limit!");
                    sleepTime = JOptionPane.showInputDialog(frame, "Enter number number of millisecond to wait [1 to 1000]");
                }
                this.sleepTime = Integer.parseInt(sleepTime);
            } catch (NumberFormatException p) {
                this.sleepTime = 100;
            }
        });

        panel.add(q);
        panel.add(w);
        panel.add(e);
        panel.add(r);
        panel.add(t);
        panel.add(y);
        panel.add(sleepBtn);
        panel.setBackground(new Color(33, 97, 140));
        frame.add(panel);
        frame.setVisible(true);
        length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
        try {
            while (Integer.parseInt(length) > 700 || Integer.parseInt(length) < 0) {
                JOptionPane.showMessageDialog(frame, "Please choose the number in the given limit!");
                length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 50");
            }
        } catch (NumberFormatException p) {
            p.getStackTrace();
        }
    }

    private void createVisualizer(SortAlgorithm algo) {
        Thread thread = new Thread(() -> {
            SortVisualizer p = new SortVisualizer();
            SortState state = new SortState(this.getArraySize());
            SortController ctrl = new SortController(algo,state,p, this.sleepTime);
            p.setState(state);
            JFrame frame = returnFrame(p);
            ctrl.run();
        });
        thread.start();
    }

    private JFrame returnFrame(JPanel panel) {
        JFrame frame = new JFrame("Sorting");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.setPreferredSize(new Dimension(800,600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    private int getArraySize(){
        try {
            return Integer.parseInt(length);
        } catch (Exception e){
            return 50;
        }
    }
}
