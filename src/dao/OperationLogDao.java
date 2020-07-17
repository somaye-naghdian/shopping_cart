package dao;

import entity.OperationLog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class OperationLogDao {
    @Autowired
    private SessionFactory sessionFactory;

    Session session = null;
    Transaction transaction = null;

    public ArrayList<OperationLog> getOperationList(String inputStartDate, String inputEndDate) {
        List<OperationLog> operationLogList = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(inputStartDate);
            Date endDate = formatter.parse(inputEndDate);
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from OperationLog o where o.date " +
                    "between :startDate and :endDate ", OperationLog.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            operationLogList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally{
            session.close();
        }
        return(ArrayList<OperationLog>)operationLogList;
    }

}
