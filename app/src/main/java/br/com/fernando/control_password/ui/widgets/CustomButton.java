package br.com.fernando.control_password.ui.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import br.com.fernando.control_password.R;


public class CustomButton extends AppCompatButton {

    private Animation myAnim;
    private boolean isAnimFinish = true;

    public CustomButton(Context context) {
        super(context);
        myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.setAlpha(1.0f);
        } else {
            this.setAlpha(0.5f);
        }
    }

    void animateButton() {
        final double animationDuration = 250;
        myAnim.setDuration((long) animationDuration);
        BounceInterpolator interpolator = new BounceInterpolator(0.4, 9);
        myAnim.setInterpolator(interpolator);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callOnClick();
                isAnimFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.startAnimation(myAnim);
    }
}
