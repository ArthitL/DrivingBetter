package appewtc.masterung.drivingbetter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainHoldActivity extends AppCompatActivity {

    //Explicit
    private String idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hold);

        // Receive from login
        idString = getIntent().getStringExtra("id");
        Log.d("car", "idString = " + idString);

    }   // Main Method

    public void clickInformation(View view) {

        Intent obIntent = new Intent(MainHoldActivity.this, InformationActivity.class);
        obIntent.putExtra("id", idString);
        startActivity(obIntent);

    }

    public void clickRepair(View view) {

        startActivity(new Intent(MainHoldActivity.this, RepairListView.class));

    }

    public  void  clickGPS(View view) {

    }

    public void clickCenterService(View view) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle(getResources().getString(R.string.tel));
        objBuilder.setMessage("กรุณาเลือก");
        objBuilder.setCancelable(false);
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.setNeutralButton("ศูนย์บริการ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent objIntent = new Intent(MainHoldActivity.this, ServiceCenterActivity.class);
                objIntent.putExtra("Index", 1);
                startActivity(objIntent);
                dialogInterface.dismiss();

            }   // Event
        });
        objBuilder.setPositiveButton("ประกันภัย", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent obj2Intent = new Intent(MainHoldActivity.this, ServiceCenterActivity.class);
                obj2Intent.putExtra("Index", 2);
                startActivity(obj2Intent);
                dialogInterface.dismiss();

            }   // Event
        });

        objBuilder.show();

    }   // Click CenterService


}   // Main Class

