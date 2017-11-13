import java.util.Random;

public class ArrayQueues {

	private static int numberOfThreads = 32;

	public static void main(String[] args) throws QueueIsFullException, QueueIsEmptyException {

		int arraySize = 100;
		NONBLOCKING_QUEUE nonBlockingQueue = new NONBLOCKING_QUEUE(arraySize);

		int[] randomNumberList = generateRandomList();

		for (int i = 1; i <= numberOfThreads; i++) {
			System.out.println("Run number " + i + " with " + i + "  threads.");
			long startTime = System.nanoTime();
			for (int j = 0; j <= i; j++) {
				nonBlockingQueue.add(randomNumberList[j + i]);
				nonBlockingQueue.add(randomNumberList[j + i + 1]);
				nonBlockingQueue.remove();
				nonBlockingQueue.add(randomNumberList[j + i + 2]);
				nonBlockingQueue.add(randomNumberList[j + i + 3]);
				nonBlockingQueue.add(randomNumberList[j + i + 4]);
				nonBlockingQueue.remove();
				nonBlockingQueue.add(randomNumberList[j + i + 5]);
				nonBlockingQueue.add(randomNumberList[j + i + 6]);
				nonBlockingQueue.add(randomNumberList[j + i + 7]);
				nonBlockingQueue.remove();
				nonBlockingQueue.remove();
			}
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("Took " + totalTime + " nanoseconds with " + i + " threads.\n");
		}

	}

	/**
	 * Generating a random list to use for enqueueing and dequeueing as random
	 * isn't thread-safe
	 * 
	 * @return
	 */
	public static int[] generateRandomList() {

		int[] preGeneratedRandomList = new int[1000];

		Random rand = new Random();

		for (int i = 0; i < preGeneratedRandomList.length; i++) {
			preGeneratedRandomList[i] = rand.nextInt(100);
		}

		return preGeneratedRandomList;
	}
}
