package com.company.message.product;

import com.company.directory.compositeDirectory.component.FileComponent;
import com.company.directory.compositeDirectory.composite.defaultDirectory.Observer.IObserverInbox;
import com.company.directory.compositeDirectory.composite.defaultDirectory.Observer.IObserverSend;
import com.company.message.parts.Attachment;
import com.company.message.parts.Firma;
import com.company.message.parts.Subject;
import com.company.message.parts.Texto;
import com.company.message.parts.users.User;

import java.util.ArrayList;
import java.util.List;

public class Message extends FileComponent {
    private User from;
    private List<User> to;
    private Subject subject;
    private List<User> cc;
    private Firma firma;
    private Texto texto;
    private List<Attachment> attachments;

    private IObserverSend observerSend;
    private IObserverInbox observerInbox;

    public Message() {
        this.texto = new Texto("");
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public List<User> getTo() {
        return to;
    }

    public void setTo(List<User> to) {
        this.to = to;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<User> getCc() {
        return cc;
    }

    public void setCc(List<User> cc) {
        this.cc = cc;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public void addFileComponent(FileComponent fileComponent) {
        //System.out.println("Message no es un directorio");
    }

    @Override
    public void deleteFileComponent(FileComponent fileComponent) {
        //System.out.println("Message no es un directorio");
    }

    @Override
    public void moveFileComponent(FileComponent destinationFileComponent, FileComponent fileComponent) {
        //System.out.println("Message no es un directorio");
    }

    @Override
    public void searchMessage(String fromOrSubject) {
        if ((this.getFrom().getEmail().equals(fromOrSubject)) || (this.getSubject().getDescription().contains(fromOrSubject))) {
            System.out.println("______________________________________________________________________");
            System.out.println("Search: " + fromOrSubject);
            this.displayMessage();
        }
    }

    @Override
    public void display(int indent) {
        System.out.println(
                this.getIndentation(indent) +
                        this.getStringUsers(this.to) +
                        "\t" + this.getSubject().getDescription()
        );
    }

    public void displayMessage() {
        System.out.println("Email Message:");
        System.out.println("From: " + this.getFrom().getEmail());
        System.out.println("To: " + this.getStringUsers(this.getTo()));
        System.out.println("Subject: " + this.subject.getDescription());
        System.out.println("CC: " + ((this.getCc() == null) ? "-" : this.getStringUsers(this.getCc())));
        System.out.println("Text: " + this.getTexto().getDescription());
        System.out.println("Attachments: " + ((this.getAttachments() == null) ? "-" : this.getStringAttachments(this.getAttachments())));
        System.out.println("Firma: \n" + ((this.getFirma() == null) ? "-" : this.getFirma().getDescription()));
    }

    public String getStringUsers(List<User> users) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<users.size(); i++) {
            sb.append(users.get(i).getEmail());
            if(i < (users.size() - 1))
                sb.append(",");
        }

        return sb.toString();
    }

    public String getStringAttachments(List<Attachment> attachments) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<attachments.size(); i++) {
            sb.append("\n-" + attachments.get(i).getName() + "." + attachments.get(i).getExtension());
        }

        return sb.toString();
    }

    public void subscribeSend(IObserverSend observerSend) {
        this.observerSend = observerSend;
    }

    public void subscribeInbox(IObserverInbox observerInbox) {
        this.observerInbox = observerInbox;
    }

    public void sendMessage() {
        if(this.getAttachments() != null) {
            List<Attachment> attachmentsCodificado = new ArrayList<>();
            for (Attachment attachment : this.getAttachments()) {
                attachment.setName(attachment.codificarBase64(attachment.getName()));
                attachmentsCodificado.add(attachment);
            }

            this.setAttachments(attachmentsCodificado);
        }

        this.observerSend.sendMessage(this);
    }

    public void inboxMessage() {
        if(this.getAttachments() != null) {
            List<Attachment> attachmentsDecodificado = new ArrayList<>();
            for(Attachment attachment: this.getAttachments()) {
                attachment.setName(attachment.decodificarBase64(attachment.getName()));
                attachmentsDecodificado.add(attachment);
            }

            this.setAttachments(attachmentsDecodificado);
        }

        this.observerInbox.inboxMessage(this);
    }
}
