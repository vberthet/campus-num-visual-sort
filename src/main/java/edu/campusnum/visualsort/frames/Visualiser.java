package edu.campusnum.visualsort.frames;


import edu.campusnum.visualsort.model.SortController;
import edu.campusnum.visualsort.model.SortState;
import edu.campusnum.visualsort.sort.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

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

        panel = new JPanel();
        GridLayout layout = new GridLayout(3,2);
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBackground(Color.WHITE);

        JButton bubble = createSortBtn("Bubble", BubbleSort.class);
        JButton selection = createSortBtn("Selection", SelectionSort.class);
        JButton insertion = createSortBtn("Insertion", InsertionSort.class);
        JButton shell = createSortBtn("Shell", ShellSort.class);
        JButton quick = createSortBtn("Quick", QuickSort.class);
        JButton merge = createSortBtn("Merge", MergeSort.class);
        JButton heap = createSortBtn("Heap", HeapSort.class);


        sleepBtn = new JButton("Speed");
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

        JButton sizeButton = createChangeSizeButton();

        panel.add(bubble);
        panel.add(selection);
        panel.add(insertion);
        panel.add(shell);
        panel.add(quick);
        panel.add(merge);
        panel.add(heap);
        panel.add(sleepBtn);
        panel.add(sizeButton);
        panel.setBackground(new Color(33, 97, 140));
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        askArraySize();
    }

    private JButton createChangeSizeButton() {
        JButton sizeButton = new JButton("Size");

        sizeButton.addActionListener( v -> {
            askArraySize();
        });
        return sizeButton;
    }

    private void askArraySize() {
        String length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
        try {
            while (Integer.parseInt(length) > 700 || Integer.parseInt(length) < 0) {
                JOptionPane.showMessageDialog(frame, "Please choose the number in the given limit!");
                length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 50");
            }
            Visualiser.length = length;
        } catch (NumberFormatException p) {
            Visualiser.length = "270";
        }
    }

    private JButton createSortBtn(String label, Class<? extends SortAlgorithm> algoClass) {
        JButton q;
        q = new JButton(label);
        q.addActionListener(v -> {
            SortAlgorithm algo = null;
            try {
                algo = algoClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            createVisualizer(algo);
        });
        return q;
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
