package com.company.directory.compositeDirectory.composite.defaultDirectory.Observer;

import com.company.message.product.Message;

public interface IObserverInbox {
    void inboxMessage(Message message);
}
