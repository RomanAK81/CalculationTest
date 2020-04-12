package com.example.calculationtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    //    最高得分
    private static final String HIGH_SCORE = "high_score";
    //    问题的左边数字
    private static final String LEFT_NUMBER = "left_mumber";
    //    问题中间的运算符
    private static final String OPERATOR= "operator";
    //    问题的右边边数字
    private static final String RIGHT_UMBER = "right_number";
    //    正确答案
    private static final String ANSWER = "answer";
    // 存入手机上数据文件的名字
    private static final String MY_DATA = "my_data";
//    当前得分
    private static  String NOW_SCORE = "now_score";
//    算数等级
    private static final int LEVEL = 10;
//    当前结果
    private static String NOW_ANSWER = "now_answer";

    private SavedStateHandle handle;

    private boolean isWin = false;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        //判断handle中是否有最高得分,如果没有就代表程序刚刚进入
        if (!handle.contains(HIGH_SCORE)) {
            SharedPreferences spf = getApplication().getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
            //初始化程序
            handle.set(HIGH_SCORE, spf.getInt(HIGH_SCORE, 0));
            handle.set(LEFT_NUMBER,0);
            handle.set(RIGHT_UMBER,0);
            handle.set(OPERATOR,"+");
            handle.set(ANSWER,0);
            handle.set(NOW_SCORE,0);
            handle.set(NOW_ANSWER,getApplication().getResources().getString(R.string.question_answer));
        }
        this.handle = handle;
    }
    public MutableLiveData<Integer> getLeftNumber(){
        System.out.println(handle.getLiveData(LEFT_NUMBER).toString());
        return handle.getLiveData(LEFT_NUMBER);
    }
    public MutableLiveData<String> getOperator(){
        return handle.getLiveData(OPERATOR);
    }
    public MutableLiveData<Integer> getRightNumber(){
        return handle.getLiveData(RIGHT_UMBER);
    }
    public MutableLiveData<Integer> getNowScore(){
        return handle.getLiveData(NOW_SCORE);
    }
    public MutableLiveData<Integer> getHighScore(){
        return handle.getLiveData(HIGH_SCORE);
    }
    public MutableLiveData<Integer> getAnswer(){
        return handle.getLiveData(ANSWER);
    }

    public MutableLiveData<String> getNOW_ANSWER() {
        return handle.getLiveData(NOW_ANSWER);
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public void generateQuestions(){
        Random random = new Random();
        int x,y;
        x = random.nextInt(LEVEL)+1;
        y = random.nextInt(LEVEL)+1;

        int i = random.nextInt(2);
        String operator = "0";
        int answer = 0;
        if (y>x){
            int temp = x;
            x = y;
            y = temp;
        }
        switch (i){
            case 0:
                operator = "+";
                answer = x+y;
                break;
            case 1:
                operator = "-";
                answer = x-y;
                break;
        }

        getLeftNumber().setValue(x);
        getOperator().setValue(operator);
        getRightNumber().setValue(y);
        getAnswer().setValue(answer);

    }

    public void save(){
        SharedPreferences spf = getApplication().getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();
        edit.putInt(HIGH_SCORE,getHighScore().getValue());
        edit.apply();
    }
    public void answerCorrect(){
        getNowScore().setValue(getNowScore().getValue()+1);
        if (getNowScore().getValue()>getHighScore().getValue()){
            getHighScore().setValue(getNowScore().getValue());
            isWin = true;
            save();
        }
        generateQuestions();
        getNOW_ANSWER().setValue(getApplication().getResources().getString(R.string.question_answer));
    }

    public void input(int x){

        if (getNOW_ANSWER().getValue().equals(getApplication().getString(R.string.question_answer))){
            getNOW_ANSWER().setValue(String.valueOf(x));
        }else{
            getNOW_ANSWER().setValue(getNOW_ANSWER().getValue()+String.valueOf(x));
        }
    }
    public void clear(){
        getNOW_ANSWER().setValue(getApplication().getString(R.string.question_answer));
    }

}
