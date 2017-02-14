package com.example.vidhika.tryingsimpleapp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    SeekBar mySeekBar;
    TextView FVal, CVal, textDisplay;
    EditText SummerText;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress =0;
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                FVal.setText(String.valueOf(progress));
                img = (ImageView) findViewById(R.id.imageView2);
                textDisplay = (TextView)findViewById(R.id.SummerText);

                //C = (5/9)*(F-32)
                int curVal = mySeekBar.getProgress();
                if (curVal <= 40)
                {
                    textDisplay.setText("Cold");
                    img.setImageResource(R.drawable.winter);
                }
                else if (40 <= curVal & curVal < 90)
                {
                    textDisplay.setText("Just Right");
                    img.setImageResource(R.drawable.summer);
                }
                else if (curVal >= 90)
                {
                    textDisplay.setText("Rainy");
                    img.setImageResource(R.drawable.rainy);
                }
                double C = (5.0/9.0) * (curVal - 32.0);
                String s = String.valueOf((int)C);
                CVal.setText(s);
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getApplicationContext(), "Started Tracking Seekbar", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getApplicationContext(), "Stopped Tracking Seekbar", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initializeVariables()
    {
        mySeekBar = (SeekBar) findViewById(R.id.seekBar);
        mySeekBar.setMax(150);
        mySeekBar.setProgress(100);

        FVal = (TextView)findViewById(R.id.FahrenheitLabel);
        CVal = (TextView)findViewById(R.id.CelsiusLabel);
        FVal.setText("100");
        double C = (100.0 - 32.0) * (5.0/9.0);
        String s = String.valueOf((int)C);
        CVal.setText(s);

    }


}

