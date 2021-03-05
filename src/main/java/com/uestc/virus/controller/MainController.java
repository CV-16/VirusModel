package com.uestc.virus.controller;

import com.uestc.virus.common.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.Timer;

@RestController
public class MainController {


    @RequestMapping("/start")
    public Map<String, Object> start(@RequestParam("peopleSize") int peopleSize,
                                     @RequestParam("boardRate") float broadRate,
                                     @RequestParam("bedCount") int bedCount,
                                     @RequestParam("u") float u,
                                     @RequestParam("mask") float mask){
        Constants.CITY_PERSON_SIZE = peopleSize;
        Constants.BROAD_RATE = broadRate;
        Constants.BED_COUNT = bedCount;
        Constants.u = u;
        Constants.MASK_PROPORTION = mask;

        initHospital();
        initPanel();
        initInfected();
//        day = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("list", list());
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(){

        Map<String, Object> map = new HashMap<>();
        map.put("list", list());

        map.put("day", Clock.worldTime/10);
        map.put("normal", PersonPool.getInstance().getPeopleSize(Person.State.NORMAL));
        map.put("shadow", PersonPool.getInstance().getPeopleSize(Person.State.SHADOW));
        map.put("confirmed", PersonPool.getInstance().getPeopleSize(Person.State.CONFIRMED)+PersonPool.getInstance().getPeopleSize(Person.State.FREEZE));
        map.put("freeze", PersonPool.getInstance().getPeopleSize(Person.State.FREEZE));
        map.put("death", PersonPool.getInstance().getPeopleSize(Person.State.DEATH));

        return map;
    }

    private List<Map<String, Object>> list(){

        List<Person> people = PersonPool.getInstance().getPersonList();
        if (people == null) {
            return new ArrayList<>();
        }


        List<Map<String, Object>> list = new ArrayList<>();

        for (Person person : people){
            Map<String, Object> map = new HashMap<>();
            switch (person.getState()) {
                case Person.State.NORMAL: {
                    //健康人
                    map.put("lnglat", new double[]{CoordinateUtil.lngChange(person.getX()), CoordinateUtil.latChange(person.getY())});
                    map.put("style", 2);
                    break;
                }
                case Person.State.SHADOW: {
                    //潜伏期感染者
                    map.put("lnglat", new double[]{CoordinateUtil.lngChange(person.getX()), CoordinateUtil.latChange(person.getY())});
                    map.put("style", 1);
                    break;
                }

                case Person.State.CONFIRMED: {
                    //确诊患者
                    map.put("lnglat", new double[]{CoordinateUtil.lngChange(person.getX()), CoordinateUtil.latChange(person.getY())});
                    map.put("style", 0);
                    break;
                }
                default:{
                    break;
                }
            }
            list.add(map);
        }
        return list;

    }

    /**
     * 初始化画布
     */
    private void initPanel() {
        Clock clock = new Clock();
        Timer timer = new Timer();
        PersonPool.personPool = new PersonPool();
        Clock.worldTime = 0;
        timer.schedule(clock, 0, 100);//启动世界计时器，时间开始流动（突然脑补DIO台词：時は停た）
    }

    private static int hospitalWidth;

    /**
     * 初始化医院参数
     */
    private static void initHospital() {
        hospitalWidth = Hospital.getInstance().getWidth();
    }

    /**
     * 初始化初始感染者
     */
    private static void initInfected() {
        List<Person> people = PersonPool.getInstance().getPersonList();//获取所有的市民
        for (int i = 0; i < Constants.ORIGINAL_COUNT; i++) {
            Person person;
            do {
                person = people.get(new Random().nextInt(people.size() - 1));//随机挑选一个市民
            } while (person.isInfected());//如果该市民已经被感染，重新挑选
            person.beInfected();//让这个幸运的市民成为感染者
        }
    }

}
