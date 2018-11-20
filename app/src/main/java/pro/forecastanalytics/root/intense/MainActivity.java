package pro.forecastanalytics.root.intense;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openUrlBtn;
    Button searchBtn;
    Button directionBtn;
    Button makeCallBtn;
    Button sendEmailBtn;
    Button selectContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openUrlBtn = findViewById(R.id.button4);
        searchBtn = findViewById(R.id.button5);
        directionBtn = findViewById(R.id.button6);
        makeCallBtn = findViewById(R.id.button7);
        sendEmailBtn = findViewById(R.id.button8);
        selectContact = findViewById(R.id.button9);


        openUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://45.79.8.30/";
                Intent z = new Intent(Intent.ACTION_VIEW);
                z.setData(Uri.parse(url));
                startActivity(z);
            }
        });
        //Search Button
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String query = "Android";
                intent.putExtra(SearchManager.QUERY, query); // query contains search string
                startActivity(intent);
            }
        });

        directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?f=d&saddr=-1.2473453,36.7498069&daddr=-1.2647325,36.8005874"));
                intent.setComponent(new ComponentName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity"));
                startActivity(intent);
            }
        });

        makeCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0708733074"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        selectContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);

            }
        });

        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","mjwaweru007@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Greatings");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hope You are doing Fine");
                startActivity(Intent.createChooser(emailIntent, "Send email"));


            }
        });



    }

    public void open_activity(View view) {
        Intent x = new Intent(this, AnotherActivity.class);
        startActivity(x);


    }

    public void share(View view) {
        Intent y = new Intent(Intent.ACTION_SEND);
        y.setType("text/plain");
        y.putExtra(Intent.EXTRA_TEXT, "Hi, how are you doing");
        startActivity(y);

    }

    public void maps(View view) {
        // Creates an Intent that will load a map of San Francisco
        Uri gmmIntentUri = Uri.parse("geo:-1.2704336,36.8066863");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);


    }
    //Search Button


    //Launch Directions on Maps, Search, Send email,Make a call,send sms,pick contact
    //open url,
}
