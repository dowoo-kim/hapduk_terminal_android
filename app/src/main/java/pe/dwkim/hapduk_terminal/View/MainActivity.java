package pe.dwkim.hapduk_terminal.View;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import pe.dwkim.hapduk_terminal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickIntercityButton(View view) {
        Intent intent = new Intent(this, IntercityActivity.class);
        startActivity(intent);
    }

    public void clickCityButton(View view) {
        Toast.makeText(this, getString(R.string.ready_to_service),Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, CityActivity.class);
//        startActivity(intent);
    }
}