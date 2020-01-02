package com.feihong.mydict;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Core {

    //是否为声母
    public static String shenMu(StringBuffer sb){
        if(sb == null){
            return null;
        }

        String[] strs1 = new String[]{"b","p","m","f","d","t","n","l","g","k","h","j","q","x","r","z","c","s","y","w"};
        String[] strs2 = new String[]{"zh","ch","sh"};

        Set<String> set1 = new HashSet<String>();
        set1.addAll(Arrays.asList(strs1));
        Set<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList(strs2));

        if(sb.length() >= 2 && set2.contains(sb.substring(0,2))){
            String temp = sb.substring(0,2);
            sb.delete(0,2);
            return temp;
        }

        if(sb.length() >=1 && set1.contains(sb.substring(0,1))){
            String temp = sb.substring(0,1);
            sb.delete(0,1);
            return temp;
        }

        return null;
    }

    //是否为整体认读（添加了er)
    public static boolean zhenTiRenDu(StringBuffer sb){
        if(sb == null){
            return false;
        }

        String[] strs1 = new String[]{"ri","zi","ci","si","yi","wu","yu","ye","er"};
        String[] strs2 = new String[]{"zhi","chi","shi","yue","yin","yun"};
        String[] strs3 = new String[]{"yuan","ying"};

        Set<String> set1 = new HashSet<String>();
        set1.addAll(Arrays.asList(strs1));
        Set<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList(strs2));
        Set<String> set3 = new HashSet<String>();
        set3.addAll(Arrays.asList(strs3));

        if(sb.length() >= 4 && set3.contains(sb.substring(0,4))){
            sb.delete(0,4);
            return true;
        }

        if(sb.length() >= 3 && set2.contains(sb.substring(0,3))) {
            sb.delete(0, 3);
            return true;
        }

        if(sb.length() >= 2 && set1.contains(sb.substring(0,2))){
            sb.delete(0,2);
            return true;
        }

        return false;

    }

    //是否为介韵母
    public static boolean jieYunMu(StringBuffer sb, String shenmu){
        if(sb == null){
            return false;
        }

        String[] strs1 = new String[]{"ia","ua","uo"};
        String[] strs2 = new String[]{"iao","ian","uai","uan","van"};
        String[] strs3 = new String[]{"iang","iong","uang"};

        Set<String> set1 = new HashSet<String>();
        set1.addAll(Arrays.asList(strs1));
        Set<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList(strs2));
        Set<String> set3 = new HashSet<String>();
        set3.addAll(Arrays.asList(strs3));

        //参考https://wenku.baidu.com/view/23e93bd984254b35eefd34ea.html
        boolean bool = false;
        if(sb.length() >= 4 && set3.contains(sb.substring(0,4))){
            String temp = sb.substring(0,4);

            if(temp.equals("iang")){
                bool = compare(shenmu, "n","l","j","q","x");
            }else if(temp.equals("iong")){
                bool = compare(shenmu, "j","q","x");
            }else if(temp.equals("uang")){
                bool = compare(shenmu, "g","k","h","zh","ch","sh");
            }

            if(bool){
                sb.delete(0,4);
                return true;
            }else{
                //pass
            }
        }


        if(sb.length() >= 3 && set2.contains(sb.substring(0,3))) {
            String temp = sb.substring(0,3);

            if(temp.equals("iao")){
                bool = compare(shenmu, "b","p","m","d","t","n","l","j","q","x");
            }else if(temp.equals("ian")){
                bool = compare(shenmu, "b","p","m","d","t","n","l","j","q","x");
            }else if(temp.equals("uai")){
                bool = compare(shenmu, "g","k","h","zh","ch","sh");
            }else if(temp.equals("uan")){
                bool = compare(shenmu, "d","t","n","l","g","k","h","zh","ch","sh","r","z","c","s","j","q","x","y");
            }else if(temp.equals("van")){
                bool = compare(shenmu, "j","q","x","y");
            }

            if(bool){
                sb.delete(0,3);
                return true;
            }else{
                //pass
            }
        }


        if(sb.length() >= 2 && set1.contains(sb.substring(0,2))){
            String temp = sb.substring(0,2);

            if(temp.equals("ia")){
                bool = compare(shenmu, "d","l","j","q","x");
            }else if(temp.equals("ua")){
                bool = compare(shenmu, "g","k","h","zh","ch","sh");
            }else if(temp.equals("uo")){
                bool = compare(shenmu, "d","t","n","l","g","k","h","zh","ch","sh","r","z","c","s");
            }

            if(bool){
                sb.delete(0,2);
                return true;
            }else{
                //pass
            }
        }

        return false;
    }

    //是否为韵母
    public static boolean yunMu(StringBuffer sb, String shenmu){
        if(sb == null){
            return false;
        }

        String[] strs1 = new String[]{"a","o","e","i","u","v"};
        String[] strs2 = new String[]{"ai","ei","ui","ao","ou","iu","ie","ve","ue","er","an","en","in","un","vn"};
        String[] strs3 = new String[]{"ang","eng","ing","ong"};

        Set<String> set1 = new HashSet<String>();
        set1.addAll(Arrays.asList(strs1));
        Set<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList(strs2));
        Set<String> set3 = new HashSet<String>();
        set3.addAll(Arrays.asList(strs3));

        //参考https://wenku.baidu.com/view/c9607c97f46527d3250ce06a.html?rec_flag=default
        boolean bool = false;
        if(sb.length() >= 3 && set3.contains(sb.substring(0,3))){
            String temp = sb.substring(0,3);

            if(temp.equals("ang")){
                bool = compare(shenmu, "j","q","x");
            }else if(temp.equals("eng")){
                bool = compare(shenmu, "j","q","x","y");
            }else if(temp.equals("ing")){
                bool = compare(shenmu, "f","g","k","h","zh","ch","sh","r","z","c","s","y","w");
            }else if(temp.equals("ong")){
                bool = compare(shenmu,"b","p","m","f","j","q","x","sh","w");
            }

            if(bool){
                //pass
            }else{
                sb.delete(0,3);
                return true;
            }
        }

        if(sb.length() >= 2 && set2.contains(sb.substring(0,2))) {
            String temp = sb.substring(0, 2);

            if(temp.equals("ai")) {
                bool = compare(shenmu, "f","j","q","x","r","y");
            } else if(temp.equals("ei")) {
                bool = compare(shenmu, "t","k","j","q","x","zh","ch","sh","r","c","s","y");
            } else if(temp.equals("ui")) {
                bool = compare(shenmu, "b", "p", "m","f","n","l","j","q","x","y","w");
            } else if(temp.equals("ao")) {
                bool = compare(shenmu, "f","j","q","x","w");
            } else if(temp.equals("ou")) {
                bool = compare(shenmu, "b","j", "q", "x", "w");
            } else if(temp.equals("iu")) {
                bool = compare(shenmu, "b", "p", "f", "t", "g", "k", "h", "zh", "ch", "sh", "r", "z", "c", "s", "y","w");
            }else if(temp.equals("ie")) {
                bool = compare(shenmu, "f","g","k","h","zh","ch","sh","r","z","c","s","y","w");
            }else if(temp.equals("ve")) {
                bool = compare(shenmu, "b", "p", "m", "f", "d", "t", "g", "k", "h", "zh", "ch", "sh", "r", "z", "c", "s", "w");
            }else if(temp.equals("ue")) {
                bool = compare(shenmu, "b", "p", "m", "f", "d", "t", "g", "k", "h", "zh", "ch", "sh", "r", "z", "c", "s", "w");
            } else if(temp.equals("er")) {
                bool = true;
            }else if(temp.equals("an")) {
                bool = compare(shenmu, "j","q","x");
            }else if(temp.equals("en")) {
                bool = compare(shenmu, "t","l","j","q","x","y");
            }else if(temp.equals("in")) {
                bool = compare(shenmu, "f","d","t","g","k","h","zh","ch","sh","r","z","c","s","w");
            }else if(temp.equals("un")) {
                bool = compare(shenmu, "b", "p", "m", "f", "n", "w");
            }else if(temp.equals("vn")) {
                bool = compare(shenmu,"b","p","m","f","d","t","n","l","g","k","h","zh","ch","sh","r","c","s","w");
            }

            if(bool){
                //pass
            }else{
                sb.delete(0, 2);
                return true;
            }
        }

        if(sb.length() >= 1 && set1.contains(sb.substring(0,1))) {
            String temp = sb.substring(0, 1);

            if (temp.equals("a")) {
                bool = compare(shenmu, "j", "q", "x", "r");
            } else if (temp.equals("o")) {
                bool = compare(shenmu, "d", "t", "n", "l", "g", "k", "h", "j", "q", "x", "zh", "ch", "sh", "r", "z", "c", "s", "y");
            } else if (temp.equals("e")) {
                bool = compare(shenmu, "b", "p", "f", "j", "q", "x", "w");
            } else if (temp.equals("i")) {
                bool = compare(shenmu, "f", "g", "k", "h", "w");
            } else if (temp.equals("u")) {
                bool = false;
            } else if (temp.equals("v")) {
                bool = compare(shenmu, "b", "p", "m", "f", "d", "t", "g", "k", "h", "zh", "ch", "sh", "r", "z", "c", "s", "w");
            }

            if(bool) {
                //pass
            }else {
                sb.delete(0, 1);
                return true;
            }
        }

        return false;
    }

    //是否为零声母
    public static boolean lingShenMu(StringBuffer sb){
        if(sb == null){
            return false;
        }
        //这个规则暂时关闭，否则在处理样本时，会得到很多虽然正确但是我不想要的结果
//        String[] strs1 = new String[]{"a","o","e"};
        String[] strs2 = new String[]{"ai","ei","ao","ou","er","an","en"};
        String[] strs3 = new String[]{"ang","eng"};

//        Set<String> set1 = new HashSet<String>();
//        set1.addAll(Arrays.asList(strs1));
        Set<String> set2 = new HashSet<String>();
        set2.addAll(Arrays.asList(strs2));
        Set<String> set3 = new HashSet<String>();
        set3.addAll(Arrays.asList(strs3));

        if(sb.length() >= 3 && set3.contains(sb.substring(0,3))){
            sb.delete(0,3);
            return true;
        }

        if(sb.length() >= 2 && set2.contains(sb.substring(0,2))) {
            sb.delete(0, 2);
            return true;
        }

//        if(sb.length() >= 1 && set1.contains(sb.substring(0,1))){
//            sb.delete(0,1);
//            return true;
//        }

        return false;
    }

    //是否为一个完整的音节
    public static boolean quanPin(String part, boolean bool) {
        boolean flag = false;

        if(part != null && part.equals("")){
            return true;
        }

        StringBuffer sb = new StringBuffer(part);

        String shenmu = null;
        if(zhenTiRenDu(sb)){
            flag = true;
        }else if(lingShenMu(sb)){
            //是否为零声母
            flag = true;
        }else if((shenmu = shenMu(sb)) != null){
            if(jieYunMu(sb, shenmu)){
                flag = true;
            }else if(yunMu(sb, shenmu)){
                flag = true;
            }else{
                return false;
            }
        }else{
            return false;
        }

        if(bool){
            if(sb.length() == 0){
                return flag;
            }else{
                return false;
            }
        }else{
            return flag;
        }
    }


    public static boolean compare(String shenmu, String ... args){
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(args));

        return set.contains(shenmu);
    }
}
