package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.Order;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 16:31
 */
public class SelectionSort implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        int n = array.getLength();
        for (int i=0;i<n; i++){
            int min = i;
            for (int j=i+1;j<n;j++){
                if (array.compare(j,min).equals(Order.Lower)){
                    min = j;
                }
            }
            array.swap(min, i);
        }
    }
}
