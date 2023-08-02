package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.Order;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 16:31
 */
public class QuickSort implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        int n = array.getLength();
        quickSort(array, 0, n-1);

    }

    public void quickSort(ObservableArray array, int low, int high){
        if (low < high){
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi +1, high);
        }
    }

    public int partition(ObservableArray array, int low, int high){
        int pivot = high;
        int i = (low -1);

        for (int j=low; j<= high -1; j++){
            if (array.compare(j,pivot).equals(Order.Lower)){
                i++;
                array.swap(i,j);
            }
        }
        array.swap(i+1, high);
        return (i+1);
    }
}
