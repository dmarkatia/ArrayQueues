import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BLOCKING_QUEUE {
	Lock lock = new ReentrantLock();
	final int arraySize;
	public int[] nonBlockingArray;
	public static int head = 0;
	public static int tail = 0;

	public BLOCKING_QUEUE(int arraySize) {
		this.nonBlockingArray = initializeQueue(arraySize);
		this.arraySize = arraySize;
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
		lock.lock();
		boolean addStatus;
		try {
			if (isFull()) {
				// throw new QueueIsFullException("Cannot add to a full
				// queue.");
				// System.out.println("Cannot add to a full queue.");
				addStatus = false;
			} else {
				nonBlockingArray[tail % arraySize] = valueToAdd;
				tail++;
				// System.out.println(valueToAdd + " added to the queue.\n");
				addStatus = true;
			}
		} finally {
			lock.unlock();
		}
		return addStatus;
	}

	/**
	 * This method removes a value from the queue and returns the value that was
	 * removed.
	 * 
	 * @return An integer
	 * @throws QueueIsEmptyException
	 */

	int remove() throws QueueIsEmptyException {
		lock.lock();
		int valueToRemove = -1;
		try {
			if (isEmpty()) {
				// System.out.println("Cannot remove from an empty queue.");
				// throw new QueueIsEmptyException("Cannot remove from an empty
				// queue.");
			} else {
				valueToRemove = nonBlockingArray[head % arraySize];
				head++;
				// System.out.println(valueToRemove + " removed from the
				// queue.\n");

			}
		} finally {
			lock.unlock();
		}
		return valueToRemove;
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

	/**
	 * Returns the size of the queue array
	 */
	int queueSize() {
		return nonBlockingArray.length;
	}

	public void run() {
		// TODO Auto-generated method stub

	}
}
