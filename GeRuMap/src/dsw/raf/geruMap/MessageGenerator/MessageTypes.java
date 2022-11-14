package dsw.raf.geruMap.MessageGenerator;

import javax.swing.*;

public enum MessageTypes {

    INFORMATION_MESSAGE(JOptionPane.INFORMATION_MESSAGE),
    ERROR_MESSAGE(JOptionPane.ERROR_MESSAGE),
    WARNING_MESSAGE(JOptionPane.WARNING_MESSAGE),
    QUESTION_MESSAGE(JOptionPane.QUESTION_MESSAGE),
    PLAIN_MESSAGE(JOptionPane.PLAIN_MESSAGE);

    private int value;
    MessageTypes(int value) {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
}
