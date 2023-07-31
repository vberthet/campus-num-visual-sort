package edu.campusnum.visualsort.model;

import edu.campusnum.visualsort.sort.SortAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 14:57
 */
public class SortController implements Runnable {

    private final SortAlgorithm algorithm;
    private final SortState state;
    private final JComponent view;
    private final int sleep;

    public SortController(
            SortAlgorithm algorithm,
            SortState state,
            JComponent view,
            int sleep
    ) {
        this.algorithm = algorithm;
        this.state = state;
        this.view = view;
        this.sleep = sleep;
        this.state.setCtrl(this);
    }

    @Override
    public void run() {
        try {
            state.setState(RunState.Sorting);
            System.out.println("Starting sort using " + algorithm.getClass().getSimpleName() + " algorithm");
            this.algorithm.sort(state.getArray());
            System.out.println("Sort using " + algorithm.getClass().getSimpleName() + " is done");
            state.setState(RunState.Done);
            SwingUtilities.invokeLater(view::repaint);
        } catch (Exception e) {
            System.out.println("Sort using " + algorithm.getClass().getSimpleName() + " is failed");
            System.out.println(e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Sorting using " + algorithm.getClass().getSimpleName() + " has failed", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void update() {
        SwingUtilities.invokeLater(view::repaint);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
