package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.Order;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 14:55
 */
public class InsertionSort  implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        int n = array.getLength();
        for (int i = 1; i < n;i++){
            int j = i;
            while (j > 0 && array.compare(j-1,j).equals(Order.Higher)){
                array.swap(j,j-1);
                j--;
            }
        }
    }
}
