package dsw.raf.geruMap.Logger;

import dsw.raf.geruMap.MessageGenerator.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileLogger extends Logger {
    FileOutputStream file;

    {
        try {
            file = new FileOutputStream(new File("Logs.txt"),true);
        } catch (FileNotFoundException e) {
            System.out.println("NE RADI");
            throw new RuntimeException(e);
        }
    }

    PrintWriter pr;

    @Override
    public void update(Object var1) {
        createLog((Message)var1);
    }


    @Override
    public void createLog(Message message) {

        try {

            pr = new PrintWriter(file);
            pr.append(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            pr.close();
        }

    }
}
