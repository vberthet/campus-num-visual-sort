package edu.campusnum.visualsort.model;

import edu.campusnum.visualsort.sort.SortAlgorithm;

import javax.swing.*;
import java.awt.*;
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
        state.setState(RunState.Sorting);
        this.algorithm.sort(state.getArray().slice(5,5));
        state.setState(RunState.Done);
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
