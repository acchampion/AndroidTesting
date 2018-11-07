package edu.osu.androidtesting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainFragment extends Fragment implements View.OnClickListener {
    private EditText mZipcode, mPassword;
    private TextView mValidZipText, mValidPasswordText;
    private Button mZipButton, mPasswordButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mZipcode = v.findViewById(R.id.zipcode_text);
        mValidZipText = v.findViewById(R.id.zipcode_valid);
        mZipButton = v.findViewById(R.id.verify_zip_button);
        mZipButton.setOnClickListener(this);

        mPassword = v.findViewById(R.id.password_text);
        mValidPasswordText = v.findViewById(R.id.password_valid);
        mPasswordButton = v.findViewById(R.id.verify_password_button);
        mPasswordButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_zip_button:
                String zipCodeStr = mZipcode.getText().toString();
                if (isValidZipCode(zipCodeStr)) {
                    mValidZipText.setText("Valid");
                } else {
                    mValidZipText.setText("Invalid");
                }
                break;
            case R.id.verify_password_button:
                String passwordStr = mPassword.getText().toString();
                if (isValidPassword(passwordStr)) {
                    mValidPasswordText.setText("Valid");
                } else {
                    mValidPasswordText.setText("Invalid");
                }
                break;
        }
    }

    private boolean isValidZipCode(String zipCodeStr) {
        boolean isValid = false;

        if ((zipCodeStr.length() == 5 || zipCodeStr.length() == 9) &&
                zipCodeStr.matches("[0-9]+")) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isValidPassword(String passwordStr) {
        boolean isValid = false;

        if (passwordStr.length() >= 6) {
            isValid = true;
        }

        return isValid;
    }
}
