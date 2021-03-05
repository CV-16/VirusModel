package com.uestc.virus.common;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends TimerTask{

    public static int worldTime = 0;//世界时间

    @Override
    public void run(){
        System.out.println(worldTime);
        if (worldTime%3 == 0){
            update();
        }
        worldTime++;

    }




    private void closeTimer() {
        if (worldTime != 0) {
//            timerTask.cancel();

        }
    }

    public void update(){

        List<Person> people = PersonPool.getInstance().getPersonList();
        if (people == null) {
            return;
        }
        for (Person person : people) {
            person.update();//对各种状态的市民进行不同的处理
        }
        System.out.println("健康者人数：" + PersonPool.getInstance().getPeopleSize(Person.State.NORMAL));
        System.out.println("潜伏期人数：" + PersonPool.getInstance().getPeopleSize(Person.State.SHADOW));
        System.out.println("发病者人数：" + PersonPool.getInstance().getPeopleSize(Person.State.CONFIRMED));
    }
}
