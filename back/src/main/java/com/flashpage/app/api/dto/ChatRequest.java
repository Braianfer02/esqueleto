package com.flashpage.app.api.dto;

public class ChatRequest {

    private String employee_id;
    private String chat_id;
    private String message_id;
    private String message;
    private String origin;
    
    public ChatRequest() {
    }
    
    public ChatRequest(String employee_id, String chat_id, String message_id, String message, String origin) {
        this.employee_id = employee_id;
        this.chat_id = chat_id;
        this.message_id = message_id;
        this.message = message;
        this.origin = origin;
    }
    public String getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    public String getChat_id() {
        return chat_id;
    }
    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }
    public String getMessage_id() {
        return message_id;
    }
    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
