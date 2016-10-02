package com.example.android.breminded.ui.landing;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.breminded.R;
import com.example.android.breminded.data.BirthdayContract;
import com.example.android.breminded.data.MyDatabase;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewFragment extends Fragment {

    AppCompatSpinner spinnerGroup;
    String[] list = {"Friend", "Family", "Office", "Acquaintance"};
    String group;
    int mYear, mMonth, mDay;
    Button selectDate, saveButton, resetButton;
    TextView textViewDate;
    EditText editTextName, editTextPhone, editTextMessage;
    ArrayAdapter arrayAdapter;

    public AddNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_new, null);
        spinnerGroup = (AppCompatSpinner) v.findViewById(R.id.spinnergroup);
        selectDate = (Button) v.findViewById(R.id.button_select_date);
        textViewDate = (TextView) v.findViewById(R.id.text_view_date);
        saveButton = (Button) v.findViewById(R.id.save_button);
        resetButton = (Button) v.findViewById(R.id.reset_button);
        editTextName = (EditText) v.findViewById(R.id.edit_text_name);
        editTextPhone = (EditText) v.findViewById(R.id.edit_text_phone_no);
        editTextMessage = (EditText) v.findViewById(R.id.edit_text_message);


        spinnerGroup.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list));
        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int groupSelected, long l) {
                group = new String(list[groupSelected]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                new String("Acquaintance");
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),

                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String strMonth = String.valueOf(month), strDay = String.valueOf(day), strYear = String.valueOf(year);

                                if (month < 10) {
                                    strMonth = "0" + strMonth;
                                }

                                if (day < 10) {
                                    strDay = "0" + strDay;
                                }

                                // Handle the data here
                                textViewDate.setText(strDay + " / " + strMonth + " / " + strYear);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                String group=(String)spinnerGroup.getSelectedItem();

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               resetValues();
                 }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();
            }
        });

        // Inflate the layout for this fragment
        return v;


    }
    public void resetValues(){
        editTextName.setText("");
        editTextPhone.setText("");
        editTextMessage.setText("Happy Birthday");
        textViewDate.setText("");
    }

}


