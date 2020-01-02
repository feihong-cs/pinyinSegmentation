package com.feihong.mydict;

import com.sun.deploy.util.StringUtils;
import java.io.*;
import java.util.*;

public class Pinyin {
    private List<String> input = new ArrayList<>();
    private List<List<String>> result = new ArrayList<>();

    public Pinyin(String str){
        this.input.add(str);
    }

    public Pinyin(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = "";
        while((line = br.readLine()) != null){
            if(!line.trim().equals("")){
                this.input.add(line.trim());
            }
        }

        br.close();
    }

    private void clean(){
        Set<String> set = new HashSet<>();

        String[] strs = new String[]{"","~","`","!","@","#","$","%","^","&","*","(",")","-","_","+","=","[","]","\\","{","}","|",";","'",":","\"",",",".","/","<",">","?"};
        Set<String> samples = new HashSet<>();
        samples.addAll(Arrays.asList(strs));

        for(String str : this.input){
            StringBuffer sb = new StringBuffer();

            for(int i=0; i < str.length(); i++){
                String substring = str.substring(i,i+1).trim();
                char ch = str.charAt(i);

                if(samples.contains(substring) || Character.isDigit(ch)){
                    continue;
                }

                sb.append(str.charAt(i));
            }

            set.add(sb.toString());
        }

        this.input.clear();
        this.input.addAll(set);
    }

    public void analysis(){
        clean();
        int count = 0;
        for(String str: input){
            System.out.println("Iter: " + (++count));
            List<String> list = new ArrayList<>();
            list.add(str);
            iter(list);
        }
    }

    public void filter(){
        int count = 0;
        System.out.println("Filter: " + (++count));
        List<List<String>> removeList = new ArrayList<>();
        String[] strs = new String[]{"ai","ei","ao","ou","er","an","en","ang","eng"};
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(strs));

        for(List<String> li : this.result){
            if(li.size() < 2 || li.size() > 3){
                removeList.add(li);
                continue;
            }

            int len = 0;
            for(String str : li){
                if(set.contains(str)){
                    removeList.add(li);
                    continue;
                }
            }
        }

        this.result.removeAll(removeList);
    }

    public List<String> getAaaBbbCcc(){
        List<String> list = new ArrayList<>();
        for(List<String> li : this.result){
            list.add(StringUtils.join(li, ""));
        }

        return removeDuplicate(list);
    }

    public List<String> getAaaBC(){
        int count = 0;
        List<String> list = new ArrayList<>();
        for(List<String> li : this.result){
            System.out.println("getAaaBC: " + (++count));
            StringBuffer sb = new StringBuffer();
            sb.append(li.get(0));
            for(int i=1; i<li.size(); i++){
                sb.append(li.get(i).substring(0,1));
            }
            list.add(sb.toString());
        }

        return removeDuplicate(list);
    }

    public List<String> getABC(){
        List<String> list = new ArrayList<>();
        for(List<String> li : this.result){
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<li.size(); i++){
                sb.append(li.get(i).substring(0,1));
            }
            list.add(sb.toString());
        }

        return removeDuplicate(list);
    }

    public List<String> getABbbCcc(){
        List<String> list = new ArrayList<>();
        for(List<String> li : this.result){
            StringBuffer sb = new StringBuffer();
            sb.append(li.get(0).substring(0,1));
            for(int i=1; i<li.size(); i++){
                sb.append(li.get(i));
            }
            list.add(sb.toString());
        }

        return removeDuplicate(list);
    }

    public List<String> getAll(){
        List<String> list = new ArrayList<>();
        list.addAll(getAaaBbbCcc());
        list.addAll(getABbbCcc());
        list.addAll(getABC());
        list.addAll(getABbbCcc());

        return removeDuplicate(list);
    }

    private void iter(List<String> list){
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

    private boolean isLastPart(String str){
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

    private List<String> removeDuplicate(List<String> input){
        Set<String> sets = new HashSet<String>();
        List<String> res = new ArrayList<String>();
        sets.addAll(input);
        res.addAll(sets);
        return res;
    }

}
