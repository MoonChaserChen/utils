package ink.akira.utils.client.snowflake;

/**
 * invalid timestamp
 */
public class InvalidSystemClockException extends RuntimeException {
    public InvalidSystemClockException(String message){
        super(message);
    }
}
