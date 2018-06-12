package in.definex.PinMessages;

import in.definex.ChatSystem.Client;

public class Pin {

    private int pinId;
    private Client creator;
    private String message;

    public Pin(int pinId, Client creator, String message) {
        this.pinId = pinId;
        this.creator = creator;
        this.message = message;
    }

    public int getPinId() {
        return pinId;
    }

    public Client getCreator() {
        return creator;
    }

    public String getMessage() {
        return message;
    }
}
