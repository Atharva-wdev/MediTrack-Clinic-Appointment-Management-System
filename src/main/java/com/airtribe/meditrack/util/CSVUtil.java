package com.airtribe.meditrack.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CSVUtil {

    private CSVUtil() {
    }

    public static void writeToCSV(String filePath, List<String> data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing CSV : " + e.getMessage());
        }
    }
}