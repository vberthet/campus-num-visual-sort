package edu.campusnum.visualsort.frames;


import edu.campusnum.visualsort.model.RunState;
import edu.campusnum.visualsort.model.SortState;

import javax.swing.*;
import java.awt.*;

public class SortVisualizer extends JPanel {

    private SortState state;
    private int maxValue;

    SortVisualizer() {
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        render(g2d);
        Integer[] values = getState().getValues();
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        int colWidth = Math.round((float) width / (float) (values.length+1));
        int x = 0;
        Color fill = null;
        Color highlight = null;
        Color fillSlice = null;
        RunState state = getState().getState();
        //if (state == RunState.Sorting) {
            fill = new Color(0, 191, 255);
            fillSlice = new Color(0, 120, 200);
            highlight = new Color(128, 0, 128);
        //} else if (state == RunState.Done) {
        //    fill = new Color(34, 139, 37);
        //}
        colorBars(g2d, values, height, colWidth, x, fill, highlight, fillSlice);
        g2d.dispose();
    }

    private void colorBars(Graphics2D g2d, Integer[] values, int height, int colWidth, int x, Color fill, Color highlight, Color fillSlice) {
        for (int index = 0; index < values.length; index++) {
            g2d.setColor(fill);
            int value = values[index];
            int colHeight = (int) ((float) height * ((float) value / (float) maxValue));
            g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            if(getState().inActiveSlice(index) && highlight != null){
                g2d.setColor(fillSlice);
                g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            }
            if (getState().isSwap(index) && highlight != null) {
                g2d.setColor(highlight);
                g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            }
            x += colWidth;
        }
    }

    private void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Trebuchet MS", 0, 18));
        g2d.drawString("Comparisons = " + getState().getCompareCounter(), 0, 25);
        g2d.drawString("Array swap = " + getState().getSwapCounter(), 250, 25);
    }

    private SortState getState() {
        return state;
    }

    public void setState(SortState value) {
        if (state != value) {
            state = value;
            if (state != null) {
                maxValue = 0;
                for (int intValue : state.getArray()) {
                    maxValue = Math.max(maxValue, intValue);
                }
            }
            repaint();
        }
    }
}
