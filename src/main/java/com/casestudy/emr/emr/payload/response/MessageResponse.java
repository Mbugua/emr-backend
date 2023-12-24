package com.casestudy.emr.emr.payload.response;

/**
 * Represents a response message.
 */
public class MessageResponse {
    private String message;

    /**
     * Constructs a new MessageResponse object with the specified message.
     * 
     * @param message the message to be stored in the MessageResponse object
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Returns the current message stored in the MessageResponse object.
     * 
     * @return the current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message in the MessageResponse object to the specified
     * message.
     * 
     * @param message the new message to be set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
