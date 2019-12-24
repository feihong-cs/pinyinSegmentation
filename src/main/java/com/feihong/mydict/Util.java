package com.feihong.mydict;

import java.util.*;

public class Util {

    public static String preProcess(String str){
        StringBuffer sb = new StringBuffer();

        String[] strs = new String[]{"","~","`","!","@","#","$","%","^","&","*","(",")","-","_","+","=","[","]","\\","{","}","|",";","'",":","\"",",",".","/","<",">","?"};
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(strs));

        for(int i=0; i < str.length(); i++){
            String substring = str.substring(i,i+1).trim();
            char ch = str.charAt(i);

            if(set.contains(substring) || Character.isDigit(ch)){
                continue;
            }

            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static int minLength(List<List<String>> list){
       //传入的时候会检验list是否为空
        int len = 1000;
        for(List<String> li : list){
            if(li.size() < len){
                len = li.size();
            }
        }

        return len;
    }

    public static int maxLength(List<List<String>> list){
        //传入的时候会检验list是为空
        int len = 0;
        for(List<String> li : list){
            if(li.size() > len){
                len = li.size();
            }
        }

        return len;
    }


    public static List<List<String>> matchRule(List<List<String>> list){
        List<List<String>> newList = new ArrayList<>();
        String[] strs = new String[]{"ai","ei","ao","ou","er","an","en","ang","eng"};
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(strs));

        for(List<String> li : list){
            if(li.size() < 2 || li.size() >3){
                newList.add(li);
                continue;
            }

            int len = 0;
            for(String str : li){
                if(set.contains(str)){
                    len++;
                }
            }

            if(len > 0){
                newList.add(li);
            }
        }

        list.removeAll(newList);
        return list;
    }


    public static boolean isTop10(List<List<String>> list){
        String[] top10 = new String[]{"wang","li","zhang","liu","chen","yang","huang","zhao","zhou","wu"};
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(top10));

        for(List<String> li : list){
           if(set.contains(li.get(0))){
               return true;
           }
        }

        return false;
    }


    public static boolean isTop5(List<List<String>> list){
        String[] top10 = new String[]{"wang","li","zhang","liu","chen"};
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(top10));

        for(List<String> li : list){
            if(set.contains(li.get(0))){
                return true;
            }
        }

        return false;
    }
}
