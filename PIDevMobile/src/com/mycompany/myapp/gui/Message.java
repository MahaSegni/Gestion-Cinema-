/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


 
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Display;
import java.util.HashMap;
import java.util.Map;

public class Message {
 
    private String content = "";
     
    private String fileUri = "";
     
    private String mimeType = MIME_TEXT;
     
    private String attachmentMimeType = MIME_IMAGE_JPG;
 
    private HashMap<String, String> attachments;
     
    private boolean cloudMessageFailSilently = false;
     
    public static final String MIME_TEXT = "text/plain";
     
    public static final String MIME_HTML = "text/html";
     
    public static final String MIME_IMAGE_JPG = "image/jpeg";
     
    public static final String MIME_IMAGE_PNG = "image/png";
     
    /**
     * Constructor with the message body content
     * @param content the message content
     */
    public Message(String content){
        this.content = content;
    }
     
    /**
     * Gets the message content
     * @return content
     */
    public String getContent(){
        return content;
    }
     
    public void setAttachment(String fileUri){
        this.fileUri = fileUri;
    }
 
   
    public Map<String, String> getAttachments() {
        if(attachments == null) {
            attachments = new HashMap<String, String>();
        } 
        if(fileUri != null && attachmentMimeType != null && fileUri.length() > 0 && attachmentMimeType.length() > 0 && !attachments.containsKey(fileUri)) {
            attachments.put(fileUri, attachmentMimeType);
        }
        return attachments;
    }
     
    /**
     * Sets the message mime type.
     * 
     * @param mimeType 
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
     
    /**
     * Gets the message mime type
     * 
     * @return the mime type
     */
    public String getMimeType() {
        return mimeType;
    }
     
    /**
     * Sets the attachment mime type.
     * 
     * @param mimeType 
     */
    public void setAttachmentMimeType(String mimeType) {
        this.attachmentMimeType = mimeType;
    }
     
    /**
     * Gets the attachment mime type
     * 
     * @return 
     */
    public String getAttachmentMimeType() {
        return attachmentMimeType;
    }
     
    /**
     * Gets the message attachment file path
     * @return the file path of the attachment
     */
    public String getAttachment() {
        return fileUri;
    }
     
   
    public static void sendMessage(String [] recipients, String subject, Message msg){
        recipients=new String[] {"mahasegni00@gmail.com"};
     
       // Display.getInstance().sendMessage(recipients, subject, msg);
    }
     
 
    public void sendMessageViaCloud(String sender, String recipient, String recipientName, String subject, String plainTextBody) {
        NetworkManager.getInstance().addToQueue(createMessage(sender, recipient, recipientName, subject, plainTextBody));
    }
 
   
    public boolean sendMessageViaCloudSync(String sender, String recipient, String recipientName, String subject, String plainTextBody) {
        ConnectionRequest r = createMessage(sender, recipient, recipientName, subject, plainTextBody);
        r.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(r);
        return r.getResposeCode() == 200;
    }
     
    private ConnectionRequest createMessage(String sender, String recipient, String recipientName, String subject, String plainTextBody) {
        ConnectionRequest cr = new ConnectionRequest();
        cr.setUrl(Display.getInstance().getProperty("cloudServerURL", "https://codename-one.appspot.com/") + "sendEmailServlet");
        cr.setFailSilently(cloudMessageFailSilently);
        cr.setPost(true);
        cr.addArgument("d", Display.getInstance().getProperty("built_by_user", ""));
        cr.addArgument("from", sender);
        cr.addArgument("to", recipient);
        cr.addArgument("re", recipientName);
        cr.addArgument("subject", subject);
        if(mimeType.equals(MIME_TEXT)) {
            cr.addArgument("body", content);
        } else {
            cr.addArgument("body", plainTextBody);
            cr.addArgument("html", content);
        }
         
        return cr;
    }
 
    /**
     * Indicates whether the cloud message should produce an error dialog if sending failed
     * 
     * @return the cloudMessageFailSilently
     */
    public boolean isCloudMessageFailSilently() {
        return cloudMessageFailSilently;
    }
 
    /**
     * Indicates whether the cloud message should produce an error dialog if sending failed
     * 
     * @param cloudMessageFailSilently the cloudMessageFailSilently to set
     */
    public void setCloudMessageFailSilently(boolean cloudMessageFailSilently) {
        this.cloudMessageFailSilently = cloudMessageFailSilently;
    }
}