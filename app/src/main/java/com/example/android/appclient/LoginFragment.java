package com.example.android.appclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import net.LoginResult;
import net.PersonIDResult;
import net.RegisterRequest;
import net.RegisterResult;

import java.net.URL;

import tree.Family;
import tree.Person;


public class LoginFragment extends Fragment {

    private EditText serverHostEditText;
    private EditText serverPortEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private View view;
    private View loginButton;
    private View registerButon;
    private String authToken;
    private String personID;
    private Person person;


    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        serverHostEditText = view.findViewById(R.id.serverHostEditText);
        serverPortEditText = view.findViewById(R.id.serverPortEditText);
        userNameEditText = view.findViewById(R.id.userNameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        maleButton = view.findViewById(R.id.maleButton);
        femaleButton = view.findViewById(R.id.femaleButton);
        loginButton = view.findViewById(R.id.loginButton);
        registerButon = view.findViewById(R.id.registerButton);

        maleButton.addTextChangedListener(textWatcher);
        femaleButton.addTextChangedListener(textWatcher);

        serverPortEditText.addTextChangedListener(textWatcher);
        serverHostEditText.addTextChangedListener(textWatcher);
        userNameEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
        emailEditText.addTextChangedListener(textWatcher);
        firstNameEditText.addTextChangedListener(textWatcher);
        lastNameEditText.addTextChangedListener(textWatcher);


        loginButton.setEnabled(false);
        registerButon.setEnabled(false);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(serverHostEditText.getText().toString().equals("") || serverPortEditText.getText().toString().equals("") || userNameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), R.string.toast_login_fail, Toast.LENGTH_SHORT).show();
                }else{
                    loginButtonClicked();
                }
            }
        });

        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(serverHostEditText.getText().toString().equals("") || serverPortEditText.getText().toString().equals("") || userNameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("") || firstNameEditText.getText().toString().equals("") || lastNameEditText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                } else {
                    registerButtonClicked();
                }
            }
        });


        return view;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkText();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void checkText(){
        if(serverHostEditText.getText().toString().equals("") || serverPortEditText.getText().toString().equals("") || userNameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
            loginButton.setEnabled(false);
        }else{
            loginButton.setEnabled(true);
        }
        if(!maleButton.isChecked() && !femaleButton.isChecked()){
            registerButon.setEnabled(false);
        }else {
            if (serverHostEditText.getText().toString().equals("") || serverPortEditText.getText().toString().equals("") || userNameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("") || firstNameEditText.getText().toString().equals("") || lastNameEditText.getText().toString().equals("")) {
                registerButon.setEnabled(false);
            } else {
                registerButon.setEnabled(true);
            }
        }
    }

    private void loginButtonClicked(){

        LoginTask task = new LoginTask();

        task.execute();

    }

    public class LoginTask extends AsyncTask <URL, Void, LoginResult> {
        @Override
        protected LoginResult doInBackground(URL... urls) {
            Client client = new Client();
            LoginResult result = client.login(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), userNameEditText.getText().toString(), passwordEditText.getText().toString());
            if(result == null){
                return result;
            }
            if(result.getMessage() != null){
                return result;
            }
            authToken = result.getAuthToken();
            personID = result.getPersonID();
            PersonIDResult personIDResult = client.getPerson(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), personID, authToken);
            person = personIDResult.getPerson();
            Family.getInstance().setPersonList(client.getPeople(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            Family.getInstance().setEventList(client.getEvents(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            return result;
        }

        @Override
        protected void onPostExecute(LoginResult result){
            if(result == null){
                Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
            }else {
                if (result.getMessage() != null) {
                    Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Login success. First name: " + person.getFirstName() + " Last name: " + person.getLastName(), Toast.LENGTH_SHORT).show();
                    MapFragment mapFragment = new MapFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.Fragment, mapFragment).commit();

                }
            }
        }
    }



    private void registerButtonClicked(){

        RegisterTask task = new RegisterTask();
        task.execute();

    }

    public class RegisterTask extends AsyncTask <URL, Void, RegisterResult> {
        @Override
        protected RegisterResult doInBackground(URL... urls) {
            Client client = new Client();
            String gender;
            if(maleButton.isChecked()){
                gender = "m";
            }else{
                gender = "f";
            }
            RegisterRequest request = new RegisterRequest(userNameEditText.getText().toString(), passwordEditText.getText().toString(), emailEditText.getText().toString(), firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), gender);
            RegisterResult result = client.register(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), request);
            if(result == null){
                return result;
            }
            if(result.getMessage() != null){
                return result;
            }
            Family.getInstance().setPersonList(client.getPeople(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            Family.getInstance().setEventList(client.getEvents(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            return result;
        }

        @Override
        protected void onPostExecute(RegisterResult result){
            if(result == null){
                Toast.makeText(getActivity(), "Register failed", Toast.LENGTH_SHORT).show();
            }else {
                if (result.getMessage() != null) {
                    Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    authToken = result.getAuthToken();
                    Toast.makeText(getActivity(), "Register success. First Name: " + firstNameEditText.getText().toString() + " Last name: " + lastNameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class DataSyncTask extends AsyncTask<URL , Void, Void>{
        @Override
        protected Void doInBackground(URL... urls) {
            Client client = new Client();
            Family.getInstance().setPersonList(client.getPeople(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            Family.getInstance().setEventList(client.getEvents(serverHostEditText.getText().toString(), serverPortEditText.getText().toString(), authToken));
            return null;
        }
    }

}
