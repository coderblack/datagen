package cn.doitedu.datagen.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogBeanWrapper {
    private LogBean logBean;
    private String sessionId;
    private long lastTime;

    private boolean isExists = true;
    private boolean isPushback = false;

    private List<String> visitedPages = new ArrayList<>();

    //private String currPage;

    private int sessionMax = 0;

    public LogBeanWrapper(LogBean logBean,String sessionId,long lastTime){
        this.logBean = logBean;
        this.sessionId = sessionId;
        this.lastTime = lastTime;

    }



}
