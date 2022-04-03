package experimentthree;

import java.io.*;
import java.text.*;
import java.util.*;

public class phone {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("请输入通话开始时间，例：20210404193600：");
            String time1 = br.readLine();
            System.out.println("请输入通话结束时间，例：20210404193600：");
            String time2 = br.readLine();

            try {
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date1 = format1.parse(time1);
                Date date2 = format1.parse(time2);
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date1);
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(date2);
                long phonetime = cal2.getTime().getTime() - cal1.getTime().getTime();
                long minute;

                if (phonetime >= 0 && phonetime / 1000 < 60 || phonetime%60000 > 0) //不到一分钟按一分钟计算
                    {minute = phonetime / 1000 / 60 + 1;}
                else
                    {minute = phonetime / 1000 / 60;}

                if (cal1.get(Calendar.MONTH) == Calendar.OCTOBER && cal1.get(Calendar.DATE) >= 25 && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    //开始时间在十月最后一个周日
                    if(cal1.get(Calendar.HOUR_OF_DAY) == 2 && cal2.get(Calendar.HOUR_OF_DAY) == 2 && cal2.get(Calendar.HOUR_OF_DAY) < 3 && cal2.get(Calendar.MINUTE) <= cal1.get(Calendar.MINUTE)) {
                        //因为转换而出现通话时间为负的情况
                        minute += 60;
                        System.out.println("通话时长："+minute);
                        System.out.println("通话账单："+Money(minute));
                    }
                    else if(cal1.get(Calendar.HOUR_OF_DAY) < 2 && cal2.get(Calendar.HOUR_OF_DAY) >= 2) {
                        //开始时间是两点前，结束时间是两点后（经历第二次转换
                        System.out.println("通话时长："+minute+"或"+(minute+60));
                        System.out.println("通话账单："+Money(minute)+"或"+Money(minute+60));
                    }
                    else {
                        System.out.println("通话时长："+minute);
                        System.out.println("通话账单："+Money(minute));
                    }

                }
                else{
                    if (cal2.get(Calendar.MONTH) == Calendar.OCTOBER && cal2.get(Calendar.DATE) >= 25 && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        if (cal2.get(Calendar.HOUR_OF_DAY) >= 2)
                            minute += 60;
                    }
                    else {
                        if((cal1.get(Calendar.MONTH) == Calendar.MARCH && cal1.get(Calendar.DATE) >= 25 && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                            if(cal1.get(Calendar.HOUR_OF_DAY)<2 && cal2.get(Calendar.HOUR_OF_DAY)>=3)
                                minute -=60;
                        }
                        else {
                            if ((cal2.get(Calendar.MONTH) == Calendar.MARCH && cal2.get(Calendar.DATE) >= 25 && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {

                                if (cal2.get(Calendar.HOUR_OF_DAY) >= 3 )
                                    minute -= 60;
                            }
                        }
                    }
                    System.out.println("通话时长：" + minute);
                    System.out.println("通话账单：" + Money(minute));
                }

                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
                String starttime = format2.format(date1);
                System.out.println("通话开始时间：" + starttime);
                String endtime = format2.format(date2);
                System.out.println("通话结束时间：" + endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double Money(long minute) {
        double money = 0;
        if (minute > 1800 || minute <= 0){
            System.out.println("通话时间输入错误，请重新输入。");
        }else {
            if(minute <= 20)
                money = minute * 0.05;
            else
                money = 1+(minute-20) * 0.1;
        }
        return money;
    }
}


