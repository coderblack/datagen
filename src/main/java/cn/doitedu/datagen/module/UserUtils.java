package cn.doitedu.datagen.module;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UserUtils {

    public static String[] carriers = {"中国移动","中国联通","中国电信","小米移动","华为移动"};
    public static String[] androidDeviceTypes = {"mi5-","mi-6","mi-7","mi-8","mi-10","ry-6","ry-7","ry-8","ry-9","oppo-6","oppo-7","oppo-8","oppo-9"};
    public static String[] iosDeviceTypes = {"iphone6","iphone7","iphone8","iphone9","iphone10","iphone11"};
    public static String[] channels = {"小米应用","华为应用","豌豆荚","360应用"};
    public static String[] osVerions = {"7.0","8.0","9.0","10.0"};
    public static String[] netTypes = {"5G","4G","WIFI"};
    public static String[] resolutions = {"1024*768","1280*768","2048*1366","1366*768"};
    public static String[] appVersions = {"2.1","2.5","2.6","2.8","3.0","3.2"};
    public static String[] refers = {
            "http://www.360.com/key=hadoop",
            "http://www.360.com/key=多易spark",
            "http://www.360.com/key=多易教育flink kafka",
            "http://www.360.com/key=全网最强flink",
            "http://www.360.com/key=多易教育spark",
            "http://www.360.com/key=多易教育涛哥",
            "http://www.360.com/key=多易教育行哥",
            "http://www.360.com/key=多易教育星哥",
            "http://www.360.com/key=多易教育雨哥",
            "http://www.360.com/key=多易教育源哥",
            "http://www.360.com/key=多易教育杰哥",

            "http://www.baidu.com/key=hadoop",
            "http://www.baidu.com/key=多易spark",
            "http://www.baidu.com/key=多易教育flink kafka",
            "http://www.baidu.com/key=全网最强flink",
            "http://www.baidu.com/key=多易教育spark",
            "http://www.baidu.com/key=多易教育涛哥",
            "http://www.baidu.com/key=多易教育行哥",
            "http://www.baidu.com/key=多易教育星哥",
            "http://www.baidu.com/key=多易教育雨哥",
            "http://www.baidu.com/key=多易教育源哥",
            "http://www.baidu.com/key=多易教育杰哥",

            "http://www.sogou.com/key=hadoop",
            "http://www.sogou.com/key=多易spark",
            "http://www.sogou.com/key=多易教育flink kafka",
            "http://www.sogou.com/key=全网最强flink",
            "http://www.sogou.com/key=多易教育spark",
            "http://www.sogou.com/key=多易教育涛哥",
            "http://www.sogou.com/key=多易教育行哥",
            "http://www.sogou.com/key=多易教育星哥",
            "http://www.sogou.com/key=多易教育雨哥",
            "http://www.sogou.com/key=多易教育源哥",
            "http://www.sogou.com/key=多易教育杰哥",
            };

    public static List<ActionEvent> actionEvents = new ArrayList<>();

    public static void initial(String eventsPath) throws IOException {
        List<String> lines = FileUtils.readLines(new File(eventsPath));
        for (String line : lines) {
            ActionEvent event = JSON.parseObject(line, ActionEvent.class);
            actionEvents.add(event);
        }
    }

    public static HashMap<String,LogBean> loadHisUsers(File filePath) throws Exception {
        HashMap<String,LogBean> users = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line =  null;
        while((line=br.readLine())!=null){
            LogBean logBean = JSON.parseObject(line, LogBean.class);
            users.put(logBean.getDeviceId()+logBean.getAccount(),logBean);
        }
        br.close();
        System.out.println("历史用户数据加载完成!");
        return users;
    }

    public static String addNewUsers(String userDataPath,HashMap<String,LogBean> users,int cnt,boolean save ,List<String> gpsList) throws IOException {

        System.out.println("新用户开始生成, 历史用户最大id：" + users.size() + "本次将增加用户：" + cnt);
        int hisMaxId = users.size();
        for (int i = hisMaxId+1; i <= hisMaxId+cnt; i++) {
            LogBean logBean = new LogBean();
            // 生成的账号形如： u004078
            String account = "u"+RandomStringUtils.randomAlphabetic(5).toLowerCase();
            logBean.setAccount(account);
            logBean.setAppId("cn.doitedu.yinew");
            logBean.setAppVersion(appVersions[RandomUtils.nextInt(0,appVersions.length)]);
            logBean.setCarrier(carriers[RandomUtils.nextInt(0,carriers.length)]);
            // deviceid直接用account
            logBean.setDeviceId(RandomStringUtils.randomAlphabetic(4).toLowerCase()+"-"+RandomStringUtils.randomNumeric(4));
            logBean.setIp(RandomUtils.nextInt(10, 12) + "." + RandomUtils.nextInt(20, 22) + "." + RandomUtils.nextInt(100, 102) + "." + RandomUtils.nextInt(60, 66));

            //logBean.setLatitude(RandomUtils.nextDouble(22.0, 42.0));
            //logBean.setLongitude(RandomUtils.nextDouble(100.0, 120.0));
            String[] gpsPair = gpsList.get(RandomUtils.nextInt(0, gpsList.size())).split(",");

            logBean.setLatitude(Double.parseDouble(gpsPair[0])+RandomUtils.nextDouble(0,0.000009));
            logBean.setLongitude(Double.parseDouble(gpsPair[1])+RandomUtils.nextDouble(0,0.000009));

            if (RandomUtils.nextInt(1, 10) % 4 == 0) {
                String deviceType = androidDeviceTypes[RandomUtils.nextInt(0, androidDeviceTypes.length)];
                logBean.setDeviceType(deviceType);
                logBean.setPlatForm("android");
                logBean.setOsName(deviceType.split("-")[0]);
                logBean.setOsVersion(osVerions[RandomUtils.nextInt(0, osVerions.length)]);
                logBean.setReleaseChannel(channels[RandomUtils.nextInt(0, channels.length)]);
            } else {
                logBean.setPlatForm("apple");
                logBean.setDeviceType(iosDeviceTypes[RandomUtils.nextInt(0, iosDeviceTypes.length)]);
                logBean.setOsName("ios");
                logBean.setOsVersion(osVerions[RandomUtils.nextInt(0, osVerions.length)]);
                logBean.setReleaseChannel("apple-store");
            }
            logBean.setNetType(netTypes[RandomUtils.nextInt(0, netTypes.length)]);
            logBean.setResolution(resolutions[RandomUtils.nextInt(0, resolutions.length)]);

            logBean.setTestGuid(i);

            users.put(logBean.getDeviceId()+logBean.getAccount(),logBean);
        }

        if(save) {
            String s = saveUsers(userDataPath,users);
            return s;
        }
        return null;
    }

    public static String saveUsers(String userDataPath,HashMap<String,LogBean> users) throws IOException {

        Date date = new Date();
        String fileName = DateFormatUtils.format(date, "yyyyMMdd.HHmmss");

        String filePath = userDataPath+"/" + fileName + ".dat";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (LogBean value : users.values()) {
            String s = JSON.toJSONString(value);
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        System.out.println("用户数据保存完毕!");

        return filePath;
    }

    public static List<LogBeanWrapper> userToWrapper(HashMap<String,LogBean> users){

        List<LogBeanWrapper> wrapper = new ArrayList<>();

        for (String s : users.keySet()) {
            wrapper.add(new LogBeanWrapper(users.get(s),RandomStringUtils.randomAlphabetic(10).toLowerCase(),System.currentTimeMillis()));
        }

        return wrapper;
    }


    public static String getExternalRefer(){
        return  refers[RandomUtils.nextInt(0,refers.length)];
    }

    public static ActionEvent getRandomEvent(){
        return actionEvents.get(RandomUtils.nextInt(0,actionEvents.size()));
    }


}
