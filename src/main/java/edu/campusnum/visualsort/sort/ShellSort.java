package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 16:32
 */
public class ShellSort implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        int length = array.getLength();
        int n = 0;
        while (n < length) {
            n = (3 * n + 1);
        }

        while (n != 0) {
            n = n/3;
            for (int i=n; i < length;i++){
                int value = array.get(i);
                int j = i;
                int arrayJ = array.get(j);
                while (j > n-1 && array.get(j-n) > value){
                    arrayJ = array.get(j-n);
                    j = j-n;
                }
                arrayJ = value;
            }
        }
    }
}