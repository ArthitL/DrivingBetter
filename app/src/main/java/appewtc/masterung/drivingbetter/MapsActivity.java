package appewtc.masterung.drivingbetter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Camera;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    //Explicit
    private GoogleMap mMap;
    private double centerLatADouble = 13.66956921;
    private double centerLngADouble = 100.61620474;
    private LatLng centerLatLng;
    private double[] makerLatDoubles, markerLngDoubles;
    private LatLng[] makerLatLngs;
    private String[] carIDStrings, dateCarStrings;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Read all SQLite
        readAllSQLite();


    } //Main method

    private void readAllSQLite() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_LOGIN, null);
        int intCount = objCursor.getCount();
        makerLatDoubles = new double[intCount];
        markerLngDoubles = new double[intCount];
        makerLatLngs = new LatLng[intCount];
        carIDStrings = new String[intCount];
        dateCarStrings = new String[intCount];

        objCursor.moveToFirst();
        for (int i = 0; i < intCount; i++) {

            makerLatDoubles[i] = Double.parseDouble(objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Lat)));
            markerLngDoubles[i] = Double.parseDouble(objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Lng)));
            makerLatLngs[i] = new LatLng(makerLatDoubles[i], markerLngDoubles[i]);

            objCursor.moveToNext();
        } //for

        objCursor.close();

    } //read all SQLite


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Create Map on center
        createMapOnCenter();


        //Maker Car
        markerCar();


    }   //on Map ready

    private void markerCar() {

        for (int i = 0; i < makerLatLngs.length; i++) {

            mMap.addMarker(new MarkerOptions().position(makerLatLngs[i]));
        } //for
    } //marker car

    private void createMapOnCenter() {
        centerLatLng = new LatLng(centerLatADouble, centerLngADouble);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

    }
}      //Main class
