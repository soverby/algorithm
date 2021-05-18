package com.soverby.test;

public class SplitThis {

    public static void main(String[] args) {
        String val = "298787473|298787473|396854099|2987874734|||Keyan||Abbas|M|20121009|1|7|||9967 South Campaign Drive||South Jordan|UT|84095||2027166525||||1|20190812||1|||||||Director of Global Manufacturing||||||||Salary|||1|1|4|20200101||2020|||||||||||||";
        String[] vals = val.split("\\|");
        System.out.println(vals.length);
    }
}
