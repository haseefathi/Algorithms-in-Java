public class RandomizedQueue {

    private Item[] array;
    private int size = 0; // number of items

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
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

    }

    private void resizeArray(int newLength) {
        
    }

}
