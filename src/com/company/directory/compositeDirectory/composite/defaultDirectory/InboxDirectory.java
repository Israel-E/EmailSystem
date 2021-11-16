package com.company.directory.compositeDirectory.composite.defaultDirectory;

import com.company.directory.compositeDirectory.composite.Directory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.Observer.IObserverInbox;
import com.company.message.product.Message;

public class InboxDirectory extends Directory implements IObserverInbox {
    public InboxDirectory() {
        super("Inbox");
    }

    @Override
    public void inboxMessage(Message message) {
        this.addFileComponent(message);
    }
}
