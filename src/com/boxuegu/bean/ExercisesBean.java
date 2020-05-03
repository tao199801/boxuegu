package com.boxuegu.bean;

public class ExercisesBean {
    public int id;          //每章习题ID
    public String title;//每章习题标题
    public String content;//每章习题的数目
    public int background;//每章习题前边的序列背景
    public int subjectId;//每道习题的ID
    public String subject;//每道习题的题干
    public String a;//每道习题的A选项
    public String b;//每道习题的B选项
    public String c;//每道习题的C选项
    public String d;//每道习题的D选项
    public int answer;//每道习题的正确答案
    
	/*
	 * select为 0,1,2,3,4，的含义：
	 * 0时表示所选选项是对的 
	 * 1表示选项中的A是错误的 
	 * 2表示选项中的B是错误的 
	 * 3表示选项中的C是错误的 
	 * 4表示选项中的D是错误的
	 */
    public int select;
}