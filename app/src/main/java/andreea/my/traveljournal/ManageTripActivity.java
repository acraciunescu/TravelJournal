package andreea.my.traveljournal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import andreea.my.traveljournal.fragment_trips.RecyclerViewFragment;

public class ManageTripActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private EditText mEditTextTripName;
    private EditText mEditTextDestination;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private SeekBar mSeekBarPrice;
    private RatingBar mRatingBar;
    FirebaseFirestore db;
    String tripID;
    String action;

    private DatePickerFragment mDatePickerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_trip);

        initView();

        setUpFireBase();

        Intent intent = getIntent();
        action = intent.getStringExtra(RecyclerViewFragment.ACTION_ID);

        if (action != null && !action.isEmpty()) {
            if (action.equals(RecyclerViewFragment.ACTION_EDIT)) {
                tripID = intent.getStringExtra(RecyclerViewFragment.TRIP_KEY);
                if (tripID != null && !tripID.isEmpty()) {
                    putDataInView(tripID);
                }
            }
        }
    }

    private void putDataInView(String tripID) {
        Log.e("ManageTripActivity" , "-----I'm here---");
        DocumentReference docRef = db.collection("trips").document(tripID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                mEditTextTripName.setText(documentSnapshot.getString("trip_name"));
                mEditTextDestination.setText(documentSnapshot.getString("destination"));

                if((long) documentSnapshot.get("type")==1) {
                    mRadioButton1.setChecked(true);
                }
                else if((long) documentSnapshot.get("type")==2) {
                    mRadioButton2.setChecked(true);
                }
                else if((long) documentSnapshot.get("type")==3) {
                    mRadioButton3.setChecked(true);
                }
                mButtonStartDate.setText(java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(documentSnapshot.getDate("start_date")));
                mButtonEndDate.setText(java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(documentSnapshot.getDate("end_date")));
                mSeekBarPrice.setProgress(documentSnapshot.get("price", int.class));
                mRatingBar.setRating(documentSnapshot.get("rating" , float.class));
            }
        });
    }

    private void initView() {
        mEditTextTripName = findViewById(R.id.edittext_tripname);
        mEditTextDestination = findViewById(R.id.edittext_destination);
        mRadioButton1 = findViewById(R.id.radiobutton_cb);
        mRadioButton2 = findViewById(R.id.radiobutton_ss);
        mRadioButton3 = findViewById(R.id.radiobutton_m);
        mButtonStartDate = findViewById(R.id.button_startdate);
        mButtonEndDate = findViewById(R.id.button_enddate);
        mSeekBarPrice = findViewById(R.id.seekBar);
        mRatingBar = findViewById(R.id.ratingBar);
        mRatingBar.setEnabled(true);

        mDatePickerFragment = new DatePickerFragment();

        mButtonStartDate.setOnClickListener(this);
        mButtonEndDate.setOnClickListener(this);

    }

    private void setUpFireBase() {

        db = FirebaseFirestore.getInstance();

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

        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        if(mDatePickerFragment.getFlag()== DatePickerFragment.FLAG_START_DATE) {
            mButtonStartDate.setText(currentDateString);
        }
        else if(mDatePickerFragment.getFlag()== DatePickerFragment.FLAG_END_DATE) {
            mButtonEndDate.setText(currentDateString);
        }
    }

    public void btnSaveTripOnClick(View view) {


        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("trip_name" , mEditTextTripName.getText().toString());
        dataMap.put("destination", mEditTextDestination.getText().toString());
        dataMap.put("price" , mSeekBarPrice.getProgress());
        if(mRadioButton1.isChecked()) {
            dataMap.put("type", 1);
        } else if(mRadioButton2.isChecked()) {
            dataMap.put("type", 2);
        } else if(mRadioButton3.isChecked()) {
            dataMap.put("type", 3);
        }
        dataMap.put("rating" , mRatingBar.getRating());
        dataMap.put("ImageUrl" , "https://firebasestorage.googleapis.com/v0/b/trip-journal-db9fd.appspot.com/o/windows10-13.jpg?alt=media&token=e1cd8ab1-c11a-4670-a22d-3f28be9ccec1");
        dataMap.put("favourite", false);
        try {
            String stringStart = mButtonStartDate.getText().toString();
            String stringEnd = mButtonEndDate.getText().toString();

            //set date format
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

            Date dateStart = format.parse(stringStart);
            dataMap.put("start_date", dateStart);

            Date dateEnd = format.parse(stringEnd);
            dataMap.put("end_date" , dateEnd);
        } catch (Exception e) {        }



        //Insert/Update
        if(action.equals(RecyclerViewFragment.ACTION_ADD)) {
            db.collection("trips")
                    .add(dataMap);
        }
        else if(action.equals(RecyclerViewFragment.ACTION_EDIT)) {
            DocumentReference docRef = db.collection("trips").document(tripID);
            docRef.update(dataMap);
        }
        Intent nextActivity = new Intent(this, ProfileActivity.class);
        startActivity(nextActivity);
    }
}
