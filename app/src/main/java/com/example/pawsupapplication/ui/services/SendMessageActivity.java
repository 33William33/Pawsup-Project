package com.example.pawsupapplication.ui.services;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.ui.login.ForgotPassActivity;

import com.example.pawsupapplication.ui.login.LoginActivity;
import com.example.pawsupapplication.ui.login.PawsupEmail;
import com.example.pawsupapplication.ui.petcard.AddCard;
import com.example.pawsupapplication.user.AfterLoginActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class handles sending messag to service provider. Routes UI, and sends email.
 *
 * @author Danyal Rana
 */
public class SendMessageActivity extends AppCompatActivity {
    public String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        String userEmail = getIntent().getStringExtra("userEmail");
        System.out.println(userEmail);
    }
    public void sendEmail(View view){
        EditText emailMessageBox = findViewById(R.id.emailMessage);
        String emailMessage=emailMessageBox.getText().toString();
        String sendEmail= PawsupEmail.sendEmail;
        String sendPassword= PawsupEmail.sendPassword;
        userEmail = getIntent().getStringExtra("userEmail");
        String userId = getIntent().getStringExtra("userId");

        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
        DAO database = new DAO(view.getContext());
        String serviceEmail=database.getUserByID(userId);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendEmail, sendPassword);
            }
        });


        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(sendEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(serviceEmail));
            message.setSubject("Message Regarding Your Service");
            message.setContent("<table width='100%' style='width:100%;border-spacing:0px' border='0' cellpadding='0' cellspacing='0'>\n" +
                    "                <tbody>                    \n" +
                    "                    <tr>" +
                    "                        <td></td>\n" +
                    "                        <td align='center' width='600'>\n" +
                    "                            <img style='width: 7em;height: 7em;' src='https://pawsupinc.com/static/media/Logo.1d5ea0e8.png' alt='Pawsup Logo'><br>\n" +
                    "                            <table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-spacing:0px' bgcolor='#ffffff'>\n" +
                    "                                <tbody>\n" +
                    "                                    <tr>\n" +
                    "                                        <td align='center'><b>Message from " +userEmail+ " regarding "+getIntent().getStringExtra("prodName")+":</b><br><br> \""+ emailMessage+"\"\n" +
                    "                                        \n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "                                    <br>\n" +
                    "                                </tbody>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                        <td><br></td>\n" +
                    "                    </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>", "text/html; charset=utf-8");

            new SendMail().execute(message);
            Intent i = new Intent(this, ServiceActivity.class);
            i.putExtra("userEmail", userEmail);
            startActivity(i);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
    //Asynchronous tasks for before, during, and after the email is sent
    private class SendMail extends AsyncTask<Message, String, String> {

        //Runs before email is sent
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Sending Email, please wait", Toast.LENGTH_SHORT).show();
        }

        //Runs when email starts sending
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "sent";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "error";
            }
        }

        //If email is sent successfully, switch view and send toast.
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("sent")){
                Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

}