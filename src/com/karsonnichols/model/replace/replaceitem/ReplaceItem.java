package com.karsonnichols.model.replace.replaceitem;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReplaceItem {
    private String line;

    public ReplaceItem(String line){
        this.line = line;
    }

    public String getLine () {
        try (Scanner scanner = new Scanner(Paths.get("replace-item.csv").toAbsolutePath())){

            while (scanner.hasNextLine()){
                // scan line
                String line = scanner.nextLine();
                if(line.startsWith("//") || line.isEmpty()){
                    continue;
                }

                // splits line in to two string one for what you searching for and what to replace the line with
                String[] parts = line.split(":");
                String searchFor = parts[0];
                String changeTo = parts[1];

                System.out.println(this.change(line, searchFor, changeTo));
                return this.change(line, searchFor, changeTo);

            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return this.line + "\n";
    }

    public String change (String line, String searchFor, String changeTo) {
        if(line.contains(searchFor)){
            // trims string to match actual string and preserve white space
            String trimmed = line.trim();
            // replace the whole matched line
            String changed = line.replace(trimmed,changeTo + "\n");

            // log change
            System.out.println("line changed from: " + trimmed + "\n to: " + changed.trim());

            return changed;
        }
        return "";
    }
}
