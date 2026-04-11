package com.example.metlife.Suzana;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportFileHandler {

    private static final String FILE_NAME = "reports.bin";


    public static void writeReports(List<Reports> reportList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            oos.writeObject(reportList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Reports> readReports() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            return (List<Reports>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public static void appendReport(Reports newReport) {

        List<Reports> reports = readReports();
        reports.add(newReport);
        writeReports(reports);
    }
}