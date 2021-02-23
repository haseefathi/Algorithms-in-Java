import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array = (Item[]) new Object[1];
    private int size = 0; // number of items

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to enqueue cannot be null");
        }
        if (size == array.length) {
            resizeArray(array.length * 2);
        }
        array[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot dequeue from empty queue!");
        }
        int index = getRandomIndex();
        Item item = array[index];
        array[index] = array[--size];
        if (size < (array.length / 4)) {
            resizeArray(array.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("No sample from empty queue!");
        }
        return array[getRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int currentIndex = 0;
        private Item[] iteratorArray = (Item[]) new Object[size];

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                iteratorArray[i] = array[i];
            }
            StdRandom.shuffle(iteratorArray);
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot invoke remove operation");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No next element!");
            }
            Item item = iteratorArray[currentIndex];
            currentIndex++;
            return item;
        }


    }


    private int getRandomIndex() {
        return StdRandom.uniform(size);
    }

    private void resizeArray(int newLength) {
        Item[] newArray = (Item[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public static void main(String[] args) {


    }

}
