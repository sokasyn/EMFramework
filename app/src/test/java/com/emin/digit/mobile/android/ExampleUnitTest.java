package com.emin.digit.mobile.android;

import android.content.Context;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 *
 * JUnit 4.x+ 可以不用继承自TestCase,之前的版本需要
 * 另外,一个Module下有两个测试的文件夹,test是逻辑单元测试,不依赖设备,而androidTest需要在设备(真机或模拟器)上执行
 */
public class ExampleUnitTest {

    private Context mContext;

    /*
     * JUnit 4.x+ 采用Annotation的方式
     * @Before 每个测试方法执行之前执行,只能有一个
     */
    @Before
    public void setUp() throws Exception{
        System.out.println("setUp called..");
        this.mContext = AppApplication.getContext();
    }

    /*
     * JUnit 4.x+ 采用Annotation的方式
     * @After 每个测试方法执行之后执行,只能有一个
     */
    @After
    public void tearDown() throws Exception{
        System.out.println(" tearDown called..");
    }

    /*
     * JUnit 4.x+ 采用Annotation的方式
     * @Test 这是个测试方法,备注:在4.x 以下测试方法必须以test开头
     */
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void writePreference(){

        Context context = new MockContext();
        System.out.println("context:" + context);
    }
}