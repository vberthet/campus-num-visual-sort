package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.Order;

import java.lang.reflect.Array;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 16:31
 */
public class HeapSort implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        organize(array);
        for (int i=array.getLength()-1;i >=0;i--){
            array.swap(0, i);
            descend(array, i, 0);
        }
    }

    public void organize (ObservableArray array){
        for (int i=0; i < array.getLength()-1; i++){
            ascend(array,i);
        }
    }

    public void ascend(ObservableArray array, int i){
        if (array.get(i) > array.get(i/2)){
            array.swap(i, i/2);
            ascend(array, i/2);
        }
    }

    public void descend(ObservableArray array, int element, int index){
        int formula = 2* index +1;
        if (formula < element){
            int max;
            if (array.compare(formula,2*index).equals(Order.Lower)){
                max = formula;
            } else {
                max = 2*index;
            }
            if (array.compare(max,index).equals(Order.Lower)){
                array.swap(max, index);
                descend(array, element, max);
            }
        }
    }
}
