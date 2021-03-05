package com.uestc.virus.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 区域人群对象池
 *
 * @ClassName: PersonPool
 * @Description: 区域人群对象池，该地区假设为一个近似封闭的环境，拥有几乎不变的民众数量
 * @author: Bruce Young
 * @date: 2020年02月02日 17:21
 */
public class PersonPool {
    public static PersonPool personPool = new PersonPool();

    public static PersonPool getInstance() {
        return personPool;
    }

    List<Person> personList = new ArrayList<Person>();

    public List<Person> getPersonList() {
        return personList;
    }


    /**
     * @param state 市民类型 Person.State的值，若为-1则返回当前总数目
     * @return 获取指定人群数量
     */
    public int getPeopleSize(int state) {
        if (state == -1) {
            return personList.size();
        }
        int i = 0;
        for (Person person : personList) {
            if (person.getState() == state) {
                i++;
            }
        }
        return i;
    }
    

    public PersonPool() {
        City city = new City(400,400);//设置城市中心为坐标(400,400)
        //添加城市居民
        int i = 0;
        for (; i <= Constants.CITY_PERSON_SIZE*Constants.MASK_PROPORTION; i++){
            Random random = new Random();
            int x = (int) (100 * random.nextGaussian() + city.getCenterX());
            int y = (int) (100 * random.nextGaussian() + city.getCenterY());
            if (x > 800) {
                x = 800;
            }
            personList.add(new Person(city, x, y, Person.MaskState.HAVE_MASK));

        }
        for (; i < Constants.CITY_PERSON_SIZE; i++) {
            Random random = new Random();
            //产生N(a,b)的数：Math.sqrt(b)*random.nextGaussian()+a
//            System.out.println(random.nextInt(14000)-7000);
//            int x = (int) (random.nextInt(800));
//            int y = (int) (random.nextInt(800));
            int x = (int) (100 * random.nextGaussian() + city.getCenterX());
            int y = (int) (100 * random.nextGaussian() + city.getCenterY());
            if (x > 800) {
                x = 800;
            }
            personList.add(new Person(city, x, y, Person.MaskState.NO_MASK));
        }
    }
}
