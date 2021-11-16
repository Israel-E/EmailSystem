package com.company.directory.compositeDirectory.component;

public abstract class FileComponent {

    protected String getIndentation(int indent) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<indent; i++) {
            sb.append("    ");
        }

        return sb.toString();
    }

    public abstract void addFileComponent(FileComponent fileComponent);
    public abstract void deleteFileComponent(FileComponent fileComponent);
    public abstract void moveFileComponent(FileComponent destinationFileComponent, FileComponent fileComponent);
    public abstract void searchMessage(String fromOrSubject);
    public abstract void display(int indent);
}
