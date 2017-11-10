/**
 * This is an exception class created for when remove() is attempted on an empty
 * queue
 * 
 * @author Danish Waheed
 *
 */
public class QueueIsEmptyException extends Exception {

	private static final long serialVersionUID = 3732605380215159685L;

	public QueueIsEmptyException(String message) {
		super(message);
	}
}
