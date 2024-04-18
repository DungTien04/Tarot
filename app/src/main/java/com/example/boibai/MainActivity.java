package com.example.boibai;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boibai.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    protected ActivityMainBinding binding;
    private Animation animAlpha, animMoveUp, animMoveDown, animScalMin, animScalMax;
    private int animCount = 0;
    private int time = 3;
    private final AnimationListener animationListener = new AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            animCount++;
            resetAnim();
            handlerAnim();
        }
    };

    private void resetAnim() {
        binding.tvTitle.clearAnimation();
        binding.ivCard1.clearAnimation();
        binding.ivCard2.clearAnimation();
        binding.ivCard3.clearAnimation();
    }


    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        initAnim();
        gotoFirstScreen();
    }

    private void handlerAnim() {
        switch (animCount) {
            case 1:
                binding.tvTitle.setVisibility(View.VISIBLE);
                binding.ivCard1.startAnimation(animAlpha);
                break;
            case 2:
                binding.ivCard1.setVisibility(View.VISIBLE);
                binding.ivCard2.startAnimation(animAlpha);
                break;
            case 3:
                binding.ivCard2.setVisibility(View.VISIBLE);
                binding.ivCard3.startAnimation(animAlpha);
                break;
            case 4:
                binding.ivCard3.setVisibility(View.VISIBLE);
                gotoSecondScreen();
                break;
            case 5:
                binding.tvTitle.setVisibility(View.VISIBLE);
                binding.ivCard1.startAnimation(animMoveDown);
                break;
            case 6:
                TableRow.LayoutParams layoutParams = (TableRow.LayoutParams) binding.ivCard1.getLayoutParams();
                layoutParams.gravity = Gravity.CENTER;
                binding.ivCard1.setLayoutParams(layoutParams);
                binding.ivCard3.startAnimation(animMoveUp);
                break;
            case 7:
                layoutParams = (TableRow.LayoutParams) binding.ivCard1.getLayoutParams();
                layoutParams.gravity = Gravity.CENTER;
                binding.ivCard3.setLayoutParams(layoutParams);
                gotoThirdScreen();
                break;
            case 8:
                binding.tvTitle.setVisibility(View.VISIBLE);
                binding.ivCard1.startAnimation(animScalMin);
                break;
            case 9:
                binding.tvTitle.setVisibility(View.VISIBLE);
                binding.ivCard1.setImageResource(R.drawable.ic_maginican);
                binding.ivCard1.startAnimation(animScalMax);
                break;
            case 10:
                binding.ivCard2.startAnimation(animScalMin);
                break;
            case 11:
                binding.ivCard2.setImageResource(R.drawable.ic_death);
                binding.ivCard2.startAnimation(animScalMax);
                break;
            case 12:
                binding.ivCard3.startAnimation(animScalMin);
                break;
            case 13:
                binding.ivCard3.setImageResource(R.drawable.ic_loves);
                binding.ivCard3.startAnimation(animScalMax);
                break;
            case 14:
                binding.tvCount.setVisibility(View.VISIBLE);
                countDown();
                break;
            case 15:
                binding.ivReveal.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void countDown() {
        handler.postDelayed(() -> {
            binding.tvCount.setText(String.valueOf(time));
            time--;
            if(time>=0){
                countDown();
            }else{
                showBTReveal();
            }
        },1000);

    }

    private void showBTReveal() {
        binding.ivReveal.startAnimation(animAlpha);
    }

    private void gotoThirdScreen() {
        binding.tvTitle.setVisibility(View.INVISIBLE);
        String textTitle3 = getString(R.string.txt_text3);
        String textSelectedTitle3 = getString(R.string.txt_select_text3);
        int startSelect = textTitle3.indexOf(textSelectedTitle3);

        SpannableStringBuilder builder = new SpannableStringBuilder(textTitle3);
        ForegroundColorSpan fcs = new ForegroundColorSpan(getColor(R.color.purple));
        builder.setSpan(fcs,startSelect,startSelect+textSelectedTitle3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        binding.tvTitle.setText(builder);
        handler.postDelayed(() -> binding.tvTitle.startAnimation(animAlpha), 500);
    }

    private void gotoSecondScreen() {
        binding.tvTitle.setVisibility(View.INVISIBLE);
        binding.tvTitle.setText(R.string.txt_text2);
        handler.postDelayed(() -> binding.tvTitle.startAnimation(animAlpha), 500);

    }

    private void gotoFirstScreen() {
        String textTitle1 = getString(R.string.txt_text1);
        String textSelectedTitle1 = getString(R.string.txt_select_text1);
        int startSelect = textTitle1.indexOf(textSelectedTitle1);

        SpannableStringBuilder builder = new SpannableStringBuilder(textTitle1);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.CYAN);
        builder.setSpan(fcs,startSelect,startSelect+textSelectedTitle1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        binding.tvTitle.setText(builder);
        handler.postDelayed(() -> binding.tvTitle.startAnimation(animAlpha), 500);
    }

    private void initAnim() {
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        animMoveUp = AnimationUtils.loadAnimation(this, R.anim.anim_move_up);
        animMoveDown = AnimationUtils.loadAnimation(this, R.anim.anim_move_down);
        animScalMax = AnimationUtils.loadAnimation(this, R.anim.anim_scale_max);
        animScalMin = AnimationUtils.loadAnimation(this, R.anim.anim_scale_min);

        animAlpha.setAnimationListener(animationListener);
        animMoveUp.setAnimationListener(animationListener);
        animMoveDown.setAnimationListener(animationListener);
        animScalMax.setAnimationListener(animationListener);
        animScalMin.setAnimationListener(animationListener);
    }
}