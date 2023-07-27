package edu.campusnum.visualsort;

import edu.campusnum.visualsort.frames.Visualiser;
import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.SortState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualSortApplication {

    /*public static void main(String[] args) {
        SortState sortState = new SortState(50);
        ObservableArray array_top = sortState.getArray();
        ObservableArray array = array_top.slice(5,10);

        System.out.println(array);
        for (int i = 0; i < array.getLength(); ++i) {
            for (int j = i - 1; j >= 0 && array.get(j) > array.get(j + 1); --j) {
                array.swap(j, j + 1);
            }
        }

        System.out.println(array.get(9));
        System.out.println(array_top);
        System.out.println(array);
    } */

    public static void main(String... s) {
        new Visualiser();
    }
}