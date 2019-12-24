package com.feihong.mydict;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestIfWork {

    @Test
    public void test() throws IOException {

        String fileName = "C:\\Users\\41157\\Desktop\\字典\\target.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        Set<String> set = new HashSet<String>();

        String line = "";
        int count1 = 0;
        int count2 = 0;
        while((line = br.readLine()) != null){
            line = Util.preProcess(line);
            Analysis mytest = new Analysis(line.toLowerCase().trim());
            List<List<String>> result = mytest.process();
            result = Util.matchRule(result);
            if(result.size() == 0){
                count1++;
            }else{
                if(Util.isTop5(result)){
                    set.add(line);
                }
            }

//            System.out.println("Processing " + line);
//            for(List<String> li : result){
//                System.out.println(li);
//            }
        }

        System.out.println("不符合规则的数目：" + count1);
        System.out.println("不符合长度的数目：" + count2);

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName + ".out.txt")));
        for(String str : set){
            bw.write(str.toLowerCase());
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    @Test
    public void Test() throws IOException {
        String fileName = "C:\\Users\\41157\\Desktop\\字典\\target.txt.out.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));

        String line = "";
        while((line = br.readLine()) != null) {
            int count = 0;
            line = Util.preProcess(line);
            for(int i = 0; i < line.length(); i++){
                if(Character.isUpperCase(line.charAt(i))){
                    count++;
                }
            }

            if(count > 3 || count < 2){
                System.out.println(line);
            }
        }

        br.close();
    }
}
