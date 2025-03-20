package com.example.demo.repo;



import com.example.demo.HibernateUtil;
import com.example.demo.model.SanPham;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SanPhamRepository {
    public static List<SanPham> getAll(){
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        Query<SanPham> query =
                session.createQuery("from SanPham");
        List<SanPham> sanPhams = query.getResultList();
        return sanPhams;
    }
    public Object create (SanPham sanPhamMoi){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.save(sanPhamMoi);
        transaction.commit();
        return object;
    }
}
