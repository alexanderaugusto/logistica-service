package br.inatel.dm112.model;

public class MailRequestData {

    private String from;
    private String password;
    private String to;
    private String content;
    private byte[] attachment;
    private String subject;

    public MailRequestData() {
    }

    public MailRequestData(String from, String password, String to, String content, byte[] attachment, String subject) {
        super();
        this.from = from;
        this.password = password;
        this.to = to;
        this.content = content;
        this.attachment = attachment;
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailRequestData [from=" + from + ", password=" + password + ", to=" + to + "]";
    }
}
