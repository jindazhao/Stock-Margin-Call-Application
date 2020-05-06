package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {

    private PrintWriter printWriter;

    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");


    }

    public void write(Saveable saveable) {
        saveable.save(printWriter);
    }

    public void close() {
        printWriter.close();
    }
}
