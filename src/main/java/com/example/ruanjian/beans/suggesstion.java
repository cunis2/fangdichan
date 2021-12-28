package com.example.ruanjian.beans;

public class suggesstion {
    int pid;       //目的项目
    String state;   //更改后状态
    String suggestion;  //原因
String result;    //更改结果：交付or重置

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "evaluate{" +
                "pid=" + pid +
                ", state='" + state + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
