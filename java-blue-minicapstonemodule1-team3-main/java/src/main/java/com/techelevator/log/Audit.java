package com.techelevator.log;

import java.io.*;

public class Audit implements Closeable {
    // properties
    private File logFile;
    private PrintWriter writer;

    public Audit(String pathName){
        this.logFile = new File("audit.txt");
        if (this.logFile.exists()){
            try {
                this.writer = new PrintWriter(new FileWriter(this.logFile, true));
            } catch (IOException e) {
                System.out.println("Sorry, an error occurred.");
            }
        }
        else {
            try {
                this.writer = new PrintWriter(this.logFile);
            } catch (FileNotFoundException e) {
                System.out.println("Sorry, an error occurred.");
            }
        }
    }

    public void write(String message) {
        this.writer.println(message);
        this.writer.flush();
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

}
