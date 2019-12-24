package com.feihong.mydict;

import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public String original;
    public List<List<String>> result = new ArrayList<>();

    public Analysis(String str){
        this.original = str;
    }

    public List<List<String>> process(){
        List<String> list = new ArrayList<>();
        list.add(this.original);
        iter(list);

        return this.result;
    }

    public void iter(List<String> list){
        this.result.remove(list);
        String last = list.get(list.size() - 1);
        int index = (last.length() >= 6) ? 6 : last.length();
        for(int i = 1; i <= index; i++){
            List<String> temp = new ArrayList<>();
            temp.addAll(list);
            String start = last.substring(0,i);
            String end = last.substring(i);

            try{
                boolean t1 = Core.quanPin(start, true);
                boolean t2 = Core.quanPin(end, false);
                if(Core.quanPin(start, true) && Core.quanPin(end, false)){
                    temp.remove(temp.size() - 1);
                    temp.add(start);
                    if(!end.equals("")){
                        temp.add(end);
                    }
                    this.result.add(temp);

                    if(!isLastPart(end)){
                        iter(temp);
                    }
                }
            }catch(Exception e){
                System.out.println("Exception: " + list);
            }
        }
    }

    public boolean isLastPart(String str){
        if(str.length() > 6){
            return false;
        }

        boolean flag = true;
        for(int i = 1; i < str.length(); i++){
            String begin = str.substring(0, i);
            String end = str.substring(i);

            boolean t1 = Core.quanPin(begin,false);
            boolean t2 = Core.quanPin(end, false);

            if(Core.quanPin(begin,false) && Core.quanPin(end, false)){
                flag = false;
                break;
            }
        }

        if(flag){
            return Core.quanPin(str, true);
        }else{
            return false;
        }
    }
}
