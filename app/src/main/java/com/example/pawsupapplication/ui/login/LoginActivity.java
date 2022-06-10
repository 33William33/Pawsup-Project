package com.example.pawsupapplication.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawsupapplication.data.DAO;
import com.example.pawsupapplication.data.model.LoggedInUser;
import com.example.pawsupapplication.ui.profile.ProfileActivity;
import com.example.pawsupapplication.R;
import com.example.pawsupapplication.databinding.ActivityLoginBinding;
import com.example.pawsupapplication.ui.petcard.AddCard;
import com.example.pawsupapplication.user.AfterLoginActivity;
import com.example.pawsupapplication.user.SellerActivity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class responsible for carrying out the action of the interface when someone "login".
 * @author Android Studio, Wader
 * @version 1.1
 * @since Oct 1st 2021
 */

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        DAO database = new DAO(LoginActivity.this);
        // create admin account for sellers
        LoggedInUser admin = new LoggedInUser("admin","admin@admin.com","AdminAdmin/");
        database.addUser(admin);


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    finish();
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), database.getUsers());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), database.getUsers());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        String email = model.getDisplayName();
        String userID = model.getUserID();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent newIntent;
        //check user type (admin/customers/service providers)
        if (email.compareTo("admin@admin.com") == 0){
            newIntent = new Intent(this, SellerActivity.class);
        }else{
            newIntent = new Intent(this, AfterLoginActivity.class);
        }
        newIntent.putExtra("userEmail", email);
        startActivity(newIntent);
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    //Temporary button launcher for pet card activity
    public void launchAddPetCard(View v){

        Intent i = new Intent(this, AddCard.class);
        startActivity(i);
    }

    public void launchProfile(View v){

        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    //Moves from login page to register page when register button is clicked
    public void showRegister(View view) {
        setContentView(R.layout.activity_register);
    }

    //Moves from register page to login page when register button is clicked
    public void returnRegister(View view) {
        setContentView(R.layout.activity_login);
    }

    //Gets email, password, and confirm password fields, and verifies that password
    //inputs are valid. If valid, a toast is shown to the user.
    public void registerAccount(View view){
        DAO database = new DAO(view.getContext());
        EditText registerEmail = findViewById(R.id.createEmail);
        EditText registerPassword = findViewById(R.id.createPassword);
        EditText registerConfirmPassword = findViewById(R.id.confirmCreatePassword);

        boolean validUpperCase=false;
        boolean validLowerCase=false;
        boolean validSymbol=false;
        boolean validNumber=false;
        boolean uniqueEmail=true;

        String stringEmail=registerEmail.getText().toString();
        String stringRegPass=registerPassword.getText().toString();
        String stringRegPassConfirm=registerConfirmPassword.getText().toString();

        boolean validConfirm=(stringRegPassConfirm.equals(stringRegPass));

        //Verifies a unique email
        Map<String,ArrayList<String>> users = database.getUsers();
        if(!users.isEmpty()) {
            uniqueEmail=(users.get(stringEmail)==null);
        }

        //verifies password length
        if (stringRegPass.length()>=6) {
            for (int i = 0; i < stringRegPass.length(); i++) {
                char currentChar = stringRegPass.charAt(i);
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
            if (validUpperCase && validLowerCase && validSymbol && validNumber && validConfirm && uniqueEmail) {
                LoggedInUser newUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), stringEmail, stringRegPass);
                if (database.addUser(newUser)) {
                    Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "An Error has occurred!", Toast.LENGTH_SHORT).show();
                }
            }else if (!uniqueEmail) {
                Toast.makeText(getApplicationContext(), "Email is in use! Please try another.", Toast.LENGTH_SHORT).show();
            }
        }
        TextView testText = findViewById(R.id.testText);
        testText.setText(String.valueOf(validUpperCase)+String.valueOf(validLowerCase)+String.valueOf(validSymbol)+ String.valueOf(validNumber) + String.valueOf(validConfirm) +"||"+ stringRegPass +"||"+stringRegPassConfirm);
    }

    public void goToForgotPass(View v) {
        Intent i = new Intent(this, ForgotPassActivity.class);
        startActivity(i);
    }


}