package a310openeuler.raspicar.widgets;

import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 实现点击按钮切换按钮图片资源以及对应文字
 */
public class SwitchImageButton {
    private boolean isTouched = false;
    private final ImageButton button;
    private final TextView comment;
    private final int offImage;
    private final int onImage;
    private final int offComment;
    private final int onComment;

    public SwitchImageButton(
            ImageButton button,TextView comment,
            int offImage, int onImage,int offComment,int onComment){
        this.button = button;
        this.offImage = offImage;
        this.onImage = onImage;
        this.comment = comment;
        this.offComment = offComment;
        this.onComment = onComment;
    }

    public void changeState(){
        if (!isTouched) {
            button.setImageResource(onImage);
            comment.setText(onComment);
        }
        else{
            button.setImageResource(offImage);
            comment.setText(offComment);
        }
        isTouched = !isTouched;
    }

    public boolean isOn(){
        return isTouched;
    }
}
