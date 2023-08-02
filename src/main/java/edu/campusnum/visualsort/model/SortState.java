package edu.campusnum.visualsort.model;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Web 74 all right reserved
 * User: vincent
 * Date: 27/07/2023
 * Time: 11:39
 */
public class SortState {


    private Integer[] values;

    private ArraySlice activeSlice;

    private int swapCounter = 0;
    private int compareCounter = 0;

    private int swapLeft = -1;
    private int swapRight = -1;

    private RunState state = RunState.Waiting;
    private SortController sortController;

    /**
     * Create an array with random value of the given size
     * @param n number of element
     * @param upper max value for random generator
     */
    private static Integer[] createRandom(int n, int upper) {
        Integer[] array = new Integer[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(upper);

        }
        return array;
    }

    public SortState(int size) {
        this.values = createRandom(size,100);
    }

    public SortState(int size, int upper) {
        this.values = createRandom(size,upper);
    }

    /**
     * Set the current active slice
     * @param slice
     */
    private void setActive(ArraySlice slice){
        this.activeSlice = slice;
    }

    public void setCtrl(SortController sortController) {

        this.sortController = sortController;
    }

    /**
     * An array slice
     * It allow to manipulate internal array and track changes
     * It also allow to take a slice of the internal array
     */
    class ArraySlice implements ObservableArray{
        /**
         * Start of the slice in the top array
         */
        private final int start;
        /**
         * End of the slice to the top array
         */
        private final int end;

        /**
         * Create a new slice of the array
         * @param start
         * @param count
         */
        private ArraySlice(int start, int count) {
            this.start = start;
            this.end = start + count;
            if(this.end > values.length){
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        public ArraySlice slice(int start, int count){
            assertInbound(start);
            return new ArraySlice(this.start + start, count);
        }

        /**
         * Swap value at given position
         * @param left
         * @param right
         */
        @Override
        public void swap(int left, int right) {
            setActive();
            int tmp = this.get(left);
            values[left+this.start] = this.get(right);
            values[right+this.start] = tmp;
            SortState.this.swapCounter++;
            SortState.this.swapRight = this.start + right;
            SortState.this.swapLeft = this.start + left;
            SortState.this.sortController.update();
        }

        /**
         * Compare values at given position
         * @param left
         * @param right
         * @return
         */
        @Override
        public Order compare(int left, int right) {
            setActive();
            SortState.this.sortController.update();
            SortState.this.compareCounter++;
            if(this.get(left) == this.get(right)){
                return Order.Equals;
            } else if (this.get(left) > this.get(right)) {
                return Order.Higher;
            } else {
                return Order.Lower;
            }
        }

        /**
         * Get the length
         * @return
         */
        @Override
        public int getLength() {
            setActive();
            return this.end - this.start;
        }

        /**
         * Set the current slice as active
         */
        private void setActive() {
            SortState.this.setActive(this);
        }

        /**
         * Get the value at given position
         * @param p
         * @return
         */
        @Override
        public int get(int p){
            assertInbound(p);
            setActive();
            return values[p+start];
        }

        /**
         * Throw an exception if index is out of bound
         * @throws ArrayIndexOutOfBoundsException if index is out of bound 
         * @param index
         */
        private void assertInbound(int index) {
            if(index < 0 || (index + this.start) > (this.end - 1)){
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        /**
         * Print the array
         * @return
         */
        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(values, this.start, this.end));
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int current = 0;

                @Override
                public boolean hasNext() {
                    return ArraySlice.this.end > current;
                }

                @Override
                public Integer next() {
                    Integer next = SortState.this.values[ArraySlice.this.start + current + start];
                    this.current++;
                    return next;
                }
            };
        }
    }

    public ObservableArray getArray() {
        return new ArraySlice(0, this.values.length);
    }

    public Integer[] getValues() {
        return values;
    }

    public boolean inActiveSlice(int position) {
        if(activeSlice == null){
            return false;
        } else {
            return activeSlice.start <= position && position < activeSlice.end ;
        }
    }

    public boolean isSwap(int position) {
        return this.swapLeft == position || this.swapRight == position;
    }
    
    public int getSwapCounter() {
        return swapCounter;
    }

    public int getCompareCounter() {
        return compareCounter;
    }

    public RunState getState() {
        return state;
    }

    public void setState(RunState state) {
        this.state = state;
    }
}
