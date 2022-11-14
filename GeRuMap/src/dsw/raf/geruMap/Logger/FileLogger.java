package dsw.raf.geruMap.Logger;

import dsw.raf.geruMap.MessageGenerator.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileLogger extends Logger {
    FileOutputStream file;
    PrintWriter printWriter;




    @Override
    public void update(Object var1) {
        createLog((Message)var1);
    }


    @Override
    public void createLog(Message message) {

        try {
            file = new FileOutputStream(new File("Logs.txt"),true);
            printWriter = new PrintWriter(file);
            printWriter.append(message.toString()+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            printWriter.close();
        }

    }
}
