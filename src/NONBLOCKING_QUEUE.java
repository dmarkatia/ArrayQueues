
/**
 * This is the implementation of the lock-free queue
 * 
 * @author Danish Waheed
 * @course COP 6616
 *
 */

import java.util.Arrays;

public class NONBLOCKING_QUEUE {
	final int arraySize = 100;
	private int[] nonBlockingArray = new int[arraySize];
	public static int head = 0;
	public static int tail = 0;

	public NONBLOCKING_QUEUE(int[] nonBlockingArray) {
		this.nonBlockingArray = nonBlockingArray;
	}

	/**
	 * This method initializes an array of size arraySize to 0 and returns it.
	 * 
	 * @param arraySize
	 * @return A new array
	 */
	int[] initializeQueue(int arraySize) {
		int arrayQueue[] = new int[arraySize];
		Arrays.fill(arrayQueue, 0);
		return arrayQueue;
	}

	/**
	 * This method adds a value to the queue and returns if the value was added
	 * successfully or not
	 * 
	 * @param valueToAdd
	 * @return A boolean
	 * @throws QueueIsFullException
	 */
	boolean add(int valueToAdd) throws QueueIsFullException {
		if (isFull()) {
			throw new QueueIsFullException("Cannot add to a full queue.");
		} else {
			nonBlockingArray[tail % arraySize] = valueToAdd;
			tail++;

			return true;
		}
	}

	/**
	 * This method removes a value from the queue and returns the value that was
	 * removed.
	 * 
	 * @return An integer
	 * @throws QueueIsEmptyException
	 */
	int remove() throws QueueIsEmptyException {
		if (isEmpty()) {
			throw new QueueIsEmptyException("Cannot remove from an empty queue.");
		} else {
			int valueToRemove = nonBlockingArray[head % arraySize];
			head++;
			return valueToRemove;
		}
	}

	/**
	 * Returns whether the queue is empty or not
	 * 
	 * @return A boolean
	 */
	boolean isEmpty() {
		return tail == head ? true : false;
	}

	/**
	 * Returns whether the queue is full or not
	 * 
	 * @return A boolean
	 */
	boolean isFull() {
		return tail - head == arraySize ? true : false;
	}

}
