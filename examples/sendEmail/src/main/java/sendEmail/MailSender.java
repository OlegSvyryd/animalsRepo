package sendEmail;
/**
 * Created by aquarius on 8/7/2015.
 */

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.Properties;

    import javax.activation.DataHandler;
    import javax.activation.FileDataSource;
    import javax.mail.*;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeBodyPart;
    import javax.mail.internet.MimeMessage;
    import javax.mail.internet.MimeMultipart;


public class MailSender {

    public MailSender() {
        }

    // Receeve feedback
    public sendEmail feedback(String fromEmail, String text, String sender){

        Session session = Session.getInstance(MailServerConfig, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailServerConfig.getProperty("mail.user"), MailServerConfig.getProperty("mail.userpassword"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            // From
            message.setFrom(new InternetAddress(fromEmail));
            // To
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailServerConfig.getProperty("mail.from")));
            // Subject
            message.setSubject("Зворотній звязок з сайту від - " + sender );

            // Mail forming
            // Body
            MimeBodyPart p1 = new MimeBodyPart();
            p1.setText(text + "Відповідь прошу надіслати на -" + fromEmail);

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(p1);

            // Mail creating
            message.setContent(mp);

            // Mail sending
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Send flat e-mail
      public sendEmail newsSend(String toEmail, String text){

            Session session = Session.getInstance(MailServerConfig, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MailServerConfig.getProperty("mail.user"), MailServerConfig.getProperty("mail.userpassword"));
                }
            });

            try {
                Message message = new MimeMessage(session);
                // From
                message.setFrom(new InternetAddress(MailServerConfig.getProperty("mail.from")));
                // To
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                // Subject
                message.setSubject("Інформація від ЛКП Лев");

                // Mail forming
                // Body
                MimeBodyPart p1 = new MimeBodyPart();
                p1.setText(text);

                Multipart mp = new MimeMultipart();
                mp.addBodyPart(p1);

                // Mail creating
                message.setContent(mp);

                // Mail sending
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
          return null;
      }

    // Send e-mail with attached file
    public void newsSend(String toEmail, String text, String file){

        Session session = Session.getInstance(MailServerConfig, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailServerConfig.getProperty("mail.user"), MailServerConfig.getProperty("mail.userpassword"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            // From
            message.setFrom(new InternetAddress(MailServerConfig.getProperty("mail.from")));
            // To
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            // Subject
            message.setSubject("Інформація від ЛКП Лев");

            // Mail forming
            // Body
            MimeBodyPart p1 = new MimeBodyPart();
            p1.setText(text);

            // Attachments
            MimeBodyPart p2 = new MimeBodyPart();

            // Adding file
            FileDataSource fds = new FileDataSource(file);
            p2.setDataHandler(new DataHandler(fds));
            p2.setFileName(fds.getName());

            // Mail forming
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(p1);
            mp.addBodyPart(p2);

            // Mail creating
            message.setContent(mp);


            // Mail sending
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



        public void refreshConfig() {
            MailServerConfig.clear();
            fetchConfig();
        }

    private static Properties MailServerConfig = new Properties();
     {
        fetchConfig();
    }

    /**
     * Open a specific text file containing mail server
     * parameters.
     */
    private void fetchConfig() {
        //This file contains the javax.mail config properties mentioned above.
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("mail.properties").getFile());

        try (InputStream input = new FileInputStream(file)) {
            MailServerConfig.load(input);
        }
        catch (IOException ex){
            System.err.println("Cannot open and load mail server properties file. Put it on...");
        }
    }
 }

