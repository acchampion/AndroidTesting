package edu.osu.androidtesting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment implements View.OnClickListener {
	private EditText mZipcode, mPassword;
	private TextView mValidZipText, mValidPasswordText;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main, container, false);

		mZipcode = v.findViewById(R.id.zipcode_text);
		mValidZipText = v.findViewById(R.id.zipcode_valid);
		Button zipButton = v.findViewById(R.id.verify_zip_button);
		zipButton.setOnClickListener(this);

		mPassword = v.findViewById(R.id.password_text);
		mValidPasswordText = v.findViewById(R.id.password_valid);
		Button passwordButton = v.findViewById(R.id.verify_password_button);
		passwordButton.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		final int viewId = v.getId();

		if (viewId == R.id.verify_zip_button) {
			String zipCodeStr = mZipcode.getText().toString();
			if (isValidZipCode(zipCodeStr)) {
				mValidZipText.setText(R.string.valid_text);
			} else {
				mValidZipText.setText(R.string.invalid_text);
			}
		} else if (viewId == R.id.verify_password_button) {
			String passwordStr = mPassword.getText().toString();
			if (isValidPassword(passwordStr)) {
				mValidPasswordText.setText(R.string.valid_text);
			} else {
				mValidPasswordText.setText(R.string.invalid_text);
			}
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

		if (passwordStr.length() >= 6 && passwordStr.matches(".*[A-Z]+.*") &&
				passwordStr.matches(".*[a-z]+.*") && passwordStr.matches(".*[0-9]+.*")) {
			isValid = true;
		}

		return isValid;
	}
}
