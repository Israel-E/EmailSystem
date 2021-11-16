package com.company.client;

import com.company.directory.compositeDirectory.composite.Directory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.DraftDirectory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.InboxDirectory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.SentDirectory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.TrashDirectory;
import com.company.message.parts.Attachment;
import com.company.message.parts.Firma;
import com.company.message.parts.Subject;
import com.company.message.parts.Texto;
import com.company.message.parts.users.*;
import com.company.message.product.Message;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void Test() {
        Directory root = new Directory("Root");
        SentDirectory sentDirectory = new SentDirectory();
        InboxDirectory inboxDirectory = new InboxDirectory();
        DraftDirectory draftDirectory = new DraftDirectory();
        TrashDirectory trashDirectory = new TrashDirectory();

        //Todo message1 sent
        Message message1 = new Message();
        message1.setFrom(new Pedro());
        List<User> to1 = new ArrayList<>();
        to1.add(new Juan());
        to1.add(new Luis());
        message1.setTo(to1);
        message1.setSubject(new Subject("Test1"));
        List<User> cc1 = new ArrayList<>();
        cc1.add(new Luis());
        cc1.add(new Maria());
        message1.setCc(cc1);
        message1.setTexto(new Texto("My first test"));
        List<Attachment> attachments1 = new ArrayList<>();
        attachments1.add(new Attachment("los_pollitos1", "mp3"));
        attachments1.add(new Attachment("report1", "pdf"));
        message1.setAttachments(attachments1);
        message1.setFirma(new Firma("Pedro Galarza" +
                "\nDeveloper"));

        message1.subscribeSend(sentDirectory);
        message1.sendMessage();

        //Todo message2
        Message message2 = new Message();
        message2.setFrom(new Luis());
        List<User> to2 = new ArrayList<>();
        to2.add(new Juan());
        message2.setTo(to2);
        message2.setSubject(new Subject("Test2"));
        List<User> cc2 = new ArrayList<>();
        cc2.add(new Maria());
        message2.setCc(cc2);
        message2.setTexto(new Texto("My second test"));
        List<Attachment> attachments2 = new ArrayList<>();
        attachments2.add(new Attachment("bG9zX3BvbGxpdG9zMg==", "mp4"));
        message2.setAttachments(attachments2);

        message2.subscribeInbox(inboxDirectory);
        message2.inboxMessage();

        //Todo message3
        Message message3 = new Message();
        message3.setFrom(new Maria());
        List<User> to3 = new ArrayList<>();
        to3.add(new Luis());
        message3.setTo(to3);
        message3.setSubject(new Subject("Test3"));
        message3.setTexto(new Texto("My third test"));

        message3.subscribeInbox(inboxDirectory);
        message3.inboxMessage();

        //Todo message4
        Message message4 = new Message();
        message4.setFrom(new Maria());
        List<User> to4 = new ArrayList<>();
        to4.add(new Luis());
        message4.setTo(to4);
        message4.setSubject(new Subject("Test4"));
        message4.setTexto(new Texto("My fourth test"));

        message4.subscribeInbox(inboxDirectory);
        message4.inboxMessage();

        root.addFileComponent(inboxDirectory);
        root.addFileComponent(sentDirectory);
        root.addFileComponent(draftDirectory);
        root.addFileComponent(trashDirectory);

        Directory destacados = new Directory("Destacados");
        Directory cotizaciones = new Directory("Cotizaciones");
        Directory prueba = new Directory("Prueba");

        destacados.addFileComponent(cotizaciones);

        root.addFileComponent(destacados);
        root.addFileComponent(prueba);
        root.display(0);
        System.out.println("________________________________________________________");

        root.moveFileComponent(cotizaciones, prueba);
        root.moveFileComponent(cotizaciones, message4);
        root.display(0);
        System.out.println("________________________________________________________");

        root.deleteFileComponent(prueba);
        root.display(0);
        System.out.println("________________________________________________________");

        root.searchMessage("Test");
    }
}
