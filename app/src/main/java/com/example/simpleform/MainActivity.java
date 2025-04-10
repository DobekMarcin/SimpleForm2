package com.example.simpleform;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText edtTextName, edtTextEmail, edtTextPassword, edtTextPassRepeat;
    private RadioGroup rgGender;
    private Spinner spinnerCountry;
    private CheckBox agreementCheckBox;
    private Button btnRegister, btnPickImage;
    private TextView txtAgreement, txtGender, txtCountry, txtWarnName, txtWarnEmail, txtWarnPass, txtWarnPassRepeat;
    private ImageView imgProfile;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.parent), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yet to talk about!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });

    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");
        if (validateData()) {
            if (agreementCheckBox.isChecked()) {
                showSnackBar();

            } else {
                Toast.makeText(MainActivity.this, "Please accept the agreement", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPass.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility(View.GONE);

        String name = edtTextName.getText().toString();
        String email = edtTextEmail.getText().toString();
        String country = spinnerCountry.getSelectedItem().toString();
        String gender = "";
        if (rgGender.getCheckedRadioButtonId() == R.id.rbMale) {
            gender = "Male";
        } else if (rgGender.getCheckedRadioButtonId() == R.id.rbFemale) {
            gender = "Female";
        } else if (rgGender.getCheckedRadioButtonId() == R.id.rbOther) {
            gender = "Other";
        } else {
            gender = "Other";
        }

        String snackName = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country;

        Log.d(TAG, "showSnackBarText: " + snackName);
        Snackbar.make(parent, snackName, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", view -> {
                    edtTextName.setText("");
                    edtTextEmail.setText("");
                    edtTextPassword.setText("");
                    edtTextPassRepeat.setText("");
                    spinnerCountry.setSelection(0);

                }).show();

    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        if (edtTextName.getText().toString().isEmpty()) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your name");
            return false;
        }
        if (edtTextPassword.getText().toString().isEmpty()) {
            txtWarnPass.setVisibility(View.VISIBLE);
            txtWarnPass.setText("Enter your password");
            return false;
        }
        if (edtTextEmail.getText().toString().isEmpty()) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter your email");
            return false;
        }
        if (edtTextPassRepeat.getText().toString().isEmpty()) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Re-enter your password");
            return false;
        }
        if (!edtTextPassword.getText().toString().equals(edtTextPassRepeat.getText().toString())) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Passwords do not match");
            return false;
        }
        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        edtTextName = findViewById(R.id.edtTextName);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        edtTextPassRepeat = findViewById(R.id.edtTextPassRepeat);

        rgGender = findViewById(R.id.rgGender);
        spinnerCountry = findViewById(R.id.spinnerCountry);

        agreementCheckBox = findViewById(R.id.agreementCheckBox);
        btnRegister = findViewById(R.id.btnRegister);
        btnPickImage = findViewById(R.id.btnPickImage);
        txtAgreement = findViewById(R.id.txtAgreement);
        txtGender = findViewById(R.id.txtGender);
        txtCountry = findViewById(R.id.txtCountry);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPass = findViewById(R.id.txtWarnPass);
        txtWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);
        imgProfile = findViewById(R.id.imgProfile);
        parent = findViewById(R.id.parent);


    }
}