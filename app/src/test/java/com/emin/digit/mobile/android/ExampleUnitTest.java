package com.emin.digit.mobile.android;

import android.content.Context;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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
    public void testWhatever(){
        char a = ' ';
        int int_a = (int)a;
        debug("a to int:" + int_a);
        debug("binary:" + Integer.toBinaryString(a));
        debug("hexString:" + Integer.toHexString(a));


        String str = "10";
        char[] charArray = str.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for(int i = 0 ; i < charArray.length ; i++){
            String tmpStr = Integer.toBinaryString(charArray[i]);

            while (tmpStr.length() < 8){
                tmpStr = "0" + tmpStr;
            }
            strBuilder.append(tmpStr);
            debug("strBuilder:" + strBuilder.toString());
        }
        debug(strBuilder.toString());
    }

    @Test
    public void testGoogleCollection(){
        debug("testGoogleCollection");

        /**
         * softKeys
         * weakValues
         * 可以设置key跟value的strong，soft，weak属性。不错不错。
         * expiration(3, TimeUnit.SECONDS)设置超时时间为3秒
         *
        */
        ConcurrentMap<String, String> testMap = new MapMaker()
                .concurrencyLevel(32)
                .softKeys()
                .weakValues()
                .expiration(3, TimeUnit.SECONDS)
                .makeComputingMap(new Function<String, String>() {
            /**
             * 这里就是绑定的根据key没找到value的时候触发的function，
             * 可以将这里的返回值放到对应的key的value中！
             * @param arg0
             * @return
             */
            @Override
            public String apply(String arg0) {
                return "create:" + arg0;
            }

        });

        testMap.put("a", "testa");
        testMap.put("b", "testb");

        System.out.println(testMap.get("a"));
        System.out.println(testMap.get("b"));
        System.out.println(testMap.get("c"));

        /**
         * 这里sleep4秒钟过后，
         * 缓存都失效，再get就会根据绑定的function去获得value放在map中了。
         */
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         * 看看这里的再输出，是不是就是新的值了！~
         */

        System.out.println(testMap.get("a"));
        System.out.println(testMap.get("b"));
        System.out.println(testMap.get("c"));
    }


    private void debug(String string){
        System.out.println(string);
    }
}