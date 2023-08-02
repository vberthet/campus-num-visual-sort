package edu.campusnum.visualsort.sort;

import edu.campusnum.visualsort.model.ObservableArray;
import edu.campusnum.visualsort.model.Order;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 16:31
 */
public class MergeSort implements SortAlgorithm{
    @Override
    public void sort(ObservableArray array) {
        if (array.getLength() > 1) {
            int n = array.getLength();
            int iL = 0;
            int iR = n / 2;
            int iE = n-iR;

            ObservableArray L = array.slice(iL, iR);
            ObservableArray R = array.slice(iR, iE);

            this.sort(L);
            this.sort(R);

            while (iR - iL > 0 && n - iR > 0) {
                if (array.compare(iR,iL).equals(Order.Lower)) {
                    for (int j = iR; j > iL; j--) {
                        array.swap(j, j - 1);
                    }
                    iR++;
                }
                iL++;
            }
        }
    }

}
