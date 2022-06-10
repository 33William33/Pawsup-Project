package com.example.pawsupapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pawsupapplication.MainActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.model.LoggedInUser;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * This class is responsible for resetting the user's password,
 * This invloves verifying the account exists, sending an email to the user,
 * generating an OTP, verifying that the new password is valid, and altering
 * the user's password within the database
 *
 * @author Danyal Rana
 */
public class ForgotPassActivity extends AppCompatActivity {

    String sendEmail=PawsupEmail.sendEmail;
    String sendPassword= PawsupEmail.sendPassword;
    String currentOTP;
    String currentEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    //verifies that the email exists, and sends email with OTP to the user
    public void verifyEmail(View view){
        boolean emailExists=false;
        EditText resetEmail = findViewById(R.id.resetEmail);
        String stringEmail=resetEmail.getText().toString();
        currentEmail=stringEmail;
        DAO database = new DAO(view.getContext());
        Map<String, ArrayList<String>> users = database.getUsers();
        if(!users.isEmpty()) {
            emailExists=(users.get(stringEmail)!=null);
        }
        if(emailExists) {
            Toast.makeText(getApplicationContext(), "Account exists: "+stringEmail, Toast.LENGTH_SHORT).show();

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
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stringEmail));
                message.setSubject("PawsUp Recovery Code");
                message.setContent("<table width='100%' style='width:100%;border-spacing:0px' border='0' cellpadding='0' cellspacing='0'>\n" +
                        "                <tbody>                    \n" +
                        "                    <tr>" +
                        "                        <td></td>\n" +
                        "                        <td align='center' width='600'>\n" +
                        "                            <img style='width: 7em;height: 7em;' src='https://pawsupinc.com/static/media/Logo.1d5ea0e8.png' alt='Pawsup Logo'><br>\n" +
                        "                            <table border='0' cellpadding='0' cellspacing='0' width='100%' style='border-spacing:0px' bgcolor='#ffffff'>\n" +
                        "                                <tbody>\n" +
                        "                                    <tr>\n" +
                        "                                        <td align='center'>Your 6 digit recovery code is: "+ String.valueOf(generateOTP(6))+"\n" +
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
            } catch (MessagingException e) {
                e.printStackTrace();
            }


        }
        else{
            Toast.makeText(getApplicationContext(), "Sorry, this email has not been registered", Toast.LENGTH_SHORT).show();
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
                setContentView(R.layout.activity_reset_password_otp);

            }
            else{
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Simple function that generates an OTP of size length
    private char[] generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for(int i = 0; i< length ; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        currentOTP=String.valueOf(otp);
        return otp;
    }

    //Verifies that the OTP matches, and changes the password in the database based on the users input
    public void verifyOTP(View view){

        EditText checkOTP = findViewById(R.id.resetCodeOTP);
        String stringOTP=checkOTP.getText().toString();
        boolean validOTP=stringOTP.equals(currentOTP);
        System.out.println("Entered OTP:"+stringOTP+ ":Actual OTP:"+currentOTP + ":Equal: "+validOTP);

        if (!validOTP){
            Toast.makeText(getApplicationContext(), "Incorrect OTP, please try again", Toast.LENGTH_SHORT).show();
        }
        else{
            DAO database = new DAO(view.getContext());
            EditText resetPassword = findViewById(R.id.newPaswordOTP);
            EditText resetConfirmPassword = findViewById(R.id.newPasswordConfirmOTP);

            boolean validUpperCase=false;
            boolean validLowerCase=false;
            boolean validSymbol=false;
            boolean validNumber=false;

            String stringPassword=resetPassword.getText().toString();
            String stringPasswordConfirm=resetConfirmPassword.getText().toString();

            boolean validConfirm=(stringPasswordConfirm.equals(stringPassword));

            //verifies password length
            if (stringPassword.length()>=6) {
                for (int i = 0; i < stringPassword.length(); i++) {
                    char currentChar = stringPassword.charAt(i);
                    //Checks for an uppercase character
                    if (Character.isLetter(currentChar) && currentChar == Character.toUpperCase(currentChar)) {
                        validUpperCase = true;
                    }
                    else if (Character.isLetter(currentChar) && currentChar == Character.toLowerCase(currentChar)) {
                        validLowerCase = true;
                    } else if (!Character.isLetterOrDigit(currentChar)) {
                        validSymbol = true;
                    } else if (Character.isDigit(currentChar)) {
                        validNumber = true;
                    }

                }
                if (validUpperCase && validLowerCase && validSymbol && validNumber && validConfirm) {

                    if (database.changePass(currentEmail, stringPassword)) {
                        Toast.makeText(getApplicationContext(), "Your password has been changed!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "An Error has occurred!", Toast.LENGTH_SHORT).show();
                    }
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Password must be 6 or more characters", Toast.LENGTH_SHORT).show();
            }
        }
    }
}