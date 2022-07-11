import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class telegram extends TelegramLongPollingBot {
    boolean first=true;
    int a ,b;

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(update.getMessage().getChatId().toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (isNumeric(update.getMessage().getText())) {
                SendDocument document = new SendDocument();
                document.setChatId(update.getMessage().getChatId().toString());
                SendPhoto photo = new SendPhoto();
                photo.setChatId(update.getMessage().getChatId().toString());

                if (first) {
                    a = Integer.parseInt(update.getMessage().getText());
                    first = false;
                    message.setText("תכתוב את המספר השני:");
                } else {
                    b = Integer.parseInt(update.getMessage().getText());
                    Euclid.start(a, b);
                    first = true;
                    String text = Euclid.getTotal();

                    File file1 = new File("tr.png");
                    //File file2 = new File("C://bullet//tr.txt");

                    InputFile inputFile1 = new InputFile(file1);
                    //InputFile inputFile2 = new InputFile(file2);

                    //document.setDocument(inputFile2);
                    String[] line = text.split("\\(");
                    int imageH = line.length * 35;
                    int imageW = line[1].length() * 11;
                    BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);

                    Graphics2D g2 = image.createGraphics();
                    g2.setColor(Color.white);
                    g2.fillRect(0, 0, image.getWidth(), image.getHeight());
                    g2.setColor(Color.black);

                    Font font = new Font("Ariel", Font.TYPE1_FONT, 16);
                    g2.setFont(font);


                    for (int i = 1; i < line.length; i++) {
                        g2.drawString("(" + line[i], 40, 35 + (i * 24));
                    }
                    g2.dispose();

                    try {
                        ImageIO.write(image, "png", file1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    inputFile1.setMedia(file1);
                    photo.setPhoto(inputFile1);

                    //inputFile2.setMedia(file2);
                    //document.setDocument(inputFile2);


                    try {
                        //execute(document);
                        execute(photo);

                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }

                if (first)
                    message.setText("תכתוב את המספר הראשון:");
                else
                    message.setText("תכתוב את המספר השני:");

                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        }
    }
//    public void onRegister() {
//        super.onRegister();
//        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//        message.setText("enter the first number:");
//        try {
//            execute(message); // Call method to send the message
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public String getBotUsername() {
        // TODO
        return <here you enter user name>;
    }

    @Override
    public String getBotToken() {
        // TODO
        return <here you enter your BotToken from Telegram>;
    }

    public static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
