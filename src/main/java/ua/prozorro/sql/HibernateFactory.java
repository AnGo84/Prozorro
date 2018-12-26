package ua.prozorro.sql;

import org.hibernate.SessionFactory;

public class HibernateFactory {
    public static SessionFactory getHibernateSession(HibernateDataBaseType dbType) {
        if (dbType.equals(HibernateDataBaseType.ORACLE)) {
            return HibernateSession.instance(HibernateDataBaseType.ORACLE.getConfigFileName());
        } else if (dbType.equals(HibernateDataBaseType.MYSQL)) {
            return HibernateSession.instance(HibernateDataBaseType.MYSQL.getConfigFileName());
        }
        return HibernateSession.instance(HibernateDataBaseType.DEFAULT.getConfigFileName());
    }
}
