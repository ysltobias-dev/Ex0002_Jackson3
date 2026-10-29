package org.example;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws IOException {
        User user1 = new User();
        user1.setName("user1");
        user1.setYearOfBirth(2026);


        JsonMapper json = new JsonMapper();// objekt daten in text datei speichern die text datei format hat

        String text = json.writeValueAsString(user1);
        System.out.println(text);


        //in text datei schreiben und erstellen
        Path jsonFile = Path.of("jsonFile.json");
        Files.writeString(jsonFile, text);


        //Umkehrung
        //text datei lesen
        text = Files.readString(jsonFile);
        System.out.println(text);


        User user2 = json.readValue(jsonFile, User.class);
        System.out.println(user2);

        List list = new ArrayList();
        list.add(user1);
        list.add(user2);

        //Path für Liste -> weil in andere Text datei speichern

        Path jsonFileList = Path.of("userList.json");
        json.writeValue(jsonFileList, list);

        list = json.readValue(jsonFileList, new TypeReference<List<User>>() {
        });
        System.out.println(list);


        //Ordner struktur mit vielen Unterordnern und Json Datein -> deswegen Design Pattern(Directory) / Visitor Pattern

        Path studentDir = Path.of("student_dateien");
        DirectorySizeVisitor sizeVisitor = new DirectorySizeVisitor();
        Traversal.traversal(studentDir.toFile(),sizeVisitor);

        System.out.println(String.format("Number of files: %d",sizeVisitor.getFiles()));
        System.out.println(String.format("Number of dirs: %d",sizeVisitor.getDirs()));
        System.out.println(String.format("Size of Bytes: %d",sizeVisitor.getSize()));


        DirectoryJsonVisitor jsonVisitor = new DirectoryJsonVisitor();
        Traversal.traversal(studentDir.toFile(),jsonVisitor);


        System.out.println("Number of Json files: "+jsonVisitor.getJsonFiles().size());






    }
}
