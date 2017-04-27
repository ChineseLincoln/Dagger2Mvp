package org.unreal.common.core.core;

import android.app.Activity;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <b>类名称：</b> ActivityTaskManager <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月27日 10:21<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class ActivityTaskManager {

    private LinkedList<Activity> activityLinkedList = new LinkedList<>();

    private ActivityTaskManager() {
    }

    private static ActivityTaskManager instance;

    public static ActivityTaskManager getInstance(){
        if(null == instance){
            instance = new ActivityTaskManager();
        }
        return instance;
    }

    //向list中添加Activity
    public final ActivityTaskManager pushActivity(Activity activity){
        activityLinkedList.add(activity);
        return instance;
    }

    //结束特定的Activity(s)
    public final ActivityTaskManager finshActivities(Class<? extends Activity>... activityClasses){
        for (Activity activity : activityLinkedList) {
            if( Arrays.asList(activityClasses).contains( activity.getClass() ) ){
                activity.finish();
            }
        }
        return instance;
    }

    //结束所有的Activities
    public final ActivityTaskManager finshAllActivities() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
        return instance;
    }

    public final void removeActivity(Activity activity) {
        activityLinkedList.remove(activity);
    }
}
