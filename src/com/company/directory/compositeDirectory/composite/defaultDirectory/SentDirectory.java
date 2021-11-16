package com.company.directory.compositeDirectory.composite.defaultDirectory;

import com.company.directory.compositeDirectory.composite.Directory;
import com.company.directory.compositeDirectory.composite.defaultDirectory.Observer.IObserverSend;
import com.company.message.product.Message;

public class SentDirectory extends Directory implements IObserverSend {
    public SentDirectory() {
        super("Sent");
    }

    @Override
    public void sendMessage(Message message) {
        this.addFileComponent(message);
    }
}
