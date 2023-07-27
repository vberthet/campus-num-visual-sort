package edu.campusnum.visualsort.model;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 11:48
 */
public interface ObservableArray extends Iterable<Integer> {

    /**
     * Get the length
     * @return
     */
    public int getLength();

    /**
     * Get the value at given position
     * @param p
     * @return
     */
    public int get(int p);

    /**
     * Swap value at given position
     * @param left
     * @param right
     */
    public void swap(int left, int right);

    /**
     * Compare values at given position
     * @param left
     * @param right
     * @return
     */
    public Order compare(int left, int right);

    /**
     * returns a slice of the array , containing elements from index 'start' to 'end-1'.
     * @param left
     * @param count
     * @return
     */
    public ObservableArray slice(int left, int count);
}
