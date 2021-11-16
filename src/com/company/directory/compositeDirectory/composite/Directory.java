package com.company.directory.compositeDirectory.composite;

import com.company.directory.compositeDirectory.component.FileComponent;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileComponent {
    private String name;
    private List<FileComponent> subFileComponents;

    public Directory(String name) {
        this.name = name;
        this.subFileComponents = new ArrayList<>();
    }

    @Override
    public void addFileComponent(FileComponent fileComponent) {
        this.subFileComponents.add(fileComponent);
    }

    @Override
    public void deleteFileComponent(FileComponent fileComponent) {
        for (int i=0; i<subFileComponents.size(); i++) {
            if (subFileComponents.get(i).equals(fileComponent)) {
                subFileComponents.remove(i);
            }
            else {
                subFileComponents.get(i).deleteFileComponent(fileComponent);
            }
        }
    }

    @Override
    public void moveFileComponent(FileComponent destinationFileComponent, FileComponent fileComponent) {
        this.deleteFileComponent(fileComponent);
        this.copyFileComponent(destinationFileComponent, fileComponent);
    }

    @Override
    public void searchMessage(String from) {
        for (FileComponent fc : subFileComponents) {
            fc.searchMessage(from);
        }
    }

    @Override
    public void display(int indent) {
        System.out.println(this.getIndentation(indent) + this.name);

        //System.out.println(this.getIndentation(indent + 1) + "To_or_From\tSubject");

        for(FileComponent fc : this.subFileComponents) {
            fc.display(indent + 1);
        }
    }

    public void copyFileComponent(FileComponent destinationFileComponent, FileComponent fileComponent) {
        for (int i=0; i<subFileComponents.size(); i++) {
            //System.out.println("antes de if => " + this.name);
            if (subFileComponents.get(i).equals(destinationFileComponent)) {
                subFileComponents.get(i).deleteFileComponent(fileComponent);
                subFileComponents.get(i).addFileComponent(fileComponent);
            }
            else {
                subFileComponents.get(i).moveFileComponent(destinationFileComponent, fileComponent);
            }
        }
    }
}
