import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node front;
    private Node back;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;

        Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node newNode = new Node(item);

        newNode.next = front;
        newNode.previous = null;

        if (front == null) { // empty list...so back will also be the new node
            back = newNode;
        } else {
            front.previous = newNode;
        }

        front = newNode;
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Node newNode = new Node(item);
        newNode.next = null;
        newNode.previous = back;

        if (back == null) {
            front = newNode;
        } else {
            back.next = newNode;
        }
        back = newNode;
        size += 1;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot remove from empty list!");
        }
        Node oldFront = front;
        if (size == 1) {
            front = null;
            back = null;
        } else {
            front = front.next;
            front.previous = null;
        }
        size -= 1;
        return oldFront.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot remove from empty list!");
        }
        Node oldBack = back;
        if (size == 1) {
            front = null;
            back = null;
        } else {
            back = back.previous;
            back.next = null;
        }
        size -= 1;

        return oldBack.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = front;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot invoke remove operation");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No next element!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {


    }


}
