package andreea.my.traveljournal;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class ManageTripActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private DatePickerFragment mDatePickerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trip);

        mButtonStartDate = findViewById(R.id.button_startdate);
        mButtonEndDate = findViewById(R.id.button_enddate);

        mDatePickerFragment = new DatePickerFragment();

        mButtonStartDate.setOnClickListener(this);
        mButtonEndDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.button_startdate) {
            mDatePickerFragment.setFlag(DatePickerFragment.FLAG_START_DATE);
            mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
        } else if(id == R.id.button_enddate) {
            mDatePickerFragment.setFlag(DatePickerFragment.FLAG_END_DATE);
            mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        if(mDatePickerFragment.getFlag()== DatePickerFragment.FLAG_START_DATE) {
            mButtonStartDate.setText(currentDateString);
        }
        else if(mDatePickerFragment.getFlag()== DatePickerFragment.FLAG_END_DATE) {
            mButtonEndDate.setText(currentDateString);
        }
    }
}
