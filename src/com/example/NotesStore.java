package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {
    private final String filePath;

    public NotesStore(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public void addNote(String text) {
        try {
            List<String[]> notes = readAllNotes();
            int maxId = 0;
            for (String[] note : notes) {
                int currentId = Integer.parseInt(note[0]);
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
            int newId = maxId + 1;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(newId + ";" + text);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи заметки: " + e.getMessage());
        }
    }

    public List<String> getAllNotes() {
        List<String> result = new ArrayList<>();
        try {
            List<String[]> notes = readAllNotes();
            for (String[] note : notes) {
                result.add(note[0] + ";" + note[1]);
            }
        } catch (IOException e) {
        }
        return result;
    }

    public boolean deleteNote(int id) {
        try {
            List<String[]> notes = readAllNotes();
            List<String[]> updatedNotes = new ArrayList<>();
            boolean found = false;

            for (String[] note : notes) {
                int currentId = Integer.parseInt(note[0]);
                if (currentId != id) {
                    updatedNotes.add(note);
                } else {
                    found = true;
                }
            }

            if (found) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    for (String[] note : updatedNotes) {
                        writer.write(note[0] + ";" + note[1]);
                        writer.newLine();
                    }
                }
                return true;
            }
        } catch (IOException e) {
            System.err.println("Ошибка при удалении заметки: " + e.getMessage());
        }
        return false;
    }

    // ★★★ НОВЫЙ МЕТОД ДЛЯ КОМАНДЫ COUNT ★★★
    public int countNotes() {
        try {
            List<String[]> notes = readAllNotes();
            return notes.size();
        } catch (IOException e) {
            return 0;
        }
    }

    private List<String[]> readAllNotes() throws IOException {
        List<String[]> notes = new ArrayList<>();
        File file = new File(filePath);
        
        if (!file.exists()) {
            return notes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";", 2);
                    if (parts.length == 2) {
                        notes.add(parts);
                    }
                }
            }
        }
        return notes;
    }
}