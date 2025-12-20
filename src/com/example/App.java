package com.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование:");
            System.out.println("  --cmd=add --text=\"Текст заметки\"");
            System.out.println("  --cmd=list");
            System.out.println("  --cmd=rm --id=1");
            System.out.println("  --cmd=count");
            return;
        }

        String cmd = null;
        String text = null;
        int id = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--cmd=")) {
                cmd = args[i].substring(6);
            }
            if (args[i].startsWith("--text=")) {
                text = args[i].substring(8, args[i].length() - 1);
            }
            if (args[i].startsWith("--id=")) {
                id = Integer.parseInt(args[i].substring(5));
            }
        }

        NotesStore notesStore = new NotesStore("data/notes.csv");

        switch (cmd) {
            case "add":
                if (text == null || text.trim().isEmpty()) {
                    System.out.println("Ошибка: текст заметки не может быть пустым");
                    return;
                }
                notesStore.addNote(text);
                System.out.println("Заметка добавлена");
                break;

            case "list":
                List<String> notes = notesStore.getAllNotes();
                if (notes.isEmpty()) {
                    System.out.println("(empty)");
                } else {
                    for (String note : notes) {
                        System.out.println(note);
                    }
                }
                break;

            case "rm":
                boolean removed = notesStore.deleteNote(id);
                if (removed) {
                    System.out.println("Заметка #" + id + " удалена");
                } else {
                    System.out.println("Not found #" + id);
                }
                break;

            case "count":
                int count = notesStore.countNotes();
                System.out.println(count);
                break;

            default:
                System.out.println("Неизвестная команда: " + cmd);
        }
    }
}