package com.a310openeuler.raspicar;

import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 实现点击按钮切换按钮图片资源以及对应文字
 */
public class SwitchImageButton {
    private boolean isTouched = false;
    private ImageButton button;
    private TextView comment;
    private int offImage;
    private int onImage;
    private int offComment;
    private int onComment;

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

    //todo 点击时字体会移动
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
}
