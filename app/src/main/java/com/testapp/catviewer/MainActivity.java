package com.testapp.catviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.threecolors, R.drawable.yeonnim, R.drawable.moo, R.drawable.raegi,
            R.drawable.marilyn, R.drawable.tungtang};
    String[] names = {"삼색이","연님","무","래기","마를린","뚱땅이"};
    int index = 0;

    ImageView imageView;
    Button btnNext, btnPrev;
    TextView txtName, pageNum;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI(); //UI참조 값 가져오기
        initBtn(); //button 초기화, 리스너 설정
        initSeekBar(); // seekBar 초기화, 리스너 설정

        display(); //이미지,숫자,버튼,시크바 인덱스에 따라 보여주기

    }

    private void initSeekBar() {
        seekBar.setMax(images.length-1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                index = progress;
                setIndex(index);
                display();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void initBtn() {
        BtnOnClickListener listener = new BtnOnClickListener();
        btnNext.setOnClickListener(listener);
        btnPrev.setOnClickListener(listener);
        btnPrev.setEnabled(false);
    }

    private void initUI() {
        imageView = findViewById(R.id.imageView);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        txtName = findViewById(R.id.txtView);
        pageNum = findViewById(R.id.pageView);
        seekBar = findViewById(R.id.seekBar);
    }

    private void display() {
        imageView.setImageResource(images[index]);
        txtName.setText(names[index]);
        pageNum.setText((index+1)+"/"+names.length);
        seekBar.setProgress(index);
    }

    class BtnOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if(id==R.id.btn_prev) {index--;}
            else {index++;}
            setIndex(index);
            display();
        }
    }

    void setIndex(int index){
        btnNext.setEnabled(true);
        btnPrev.setEnabled(true);
        if (index<=0) {btnPrev.setEnabled(false);}
        if (index>=images.length-1) { btnNext.setEnabled(false); }
        display();
    }
}