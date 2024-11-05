package web.petstore.service;

import web.petstore.persistence.LogDao;

public class LogService {

    private LogDao logDao;

    public LogService() {
        this.logDao = new LogDao();
    }

    public void logUserActivity(String userId, String activityType, String activityDetail,String item_id,String order_id) {
        logDao.saveUserActivity(userId, activityType, activityDetail,item_id,order_id);
    }
}
