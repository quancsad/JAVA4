package com.example.liststudent_delete.repository;

import com.example.liststudent_delete.entity.HibernateUtil;
import com.example.liststudent_delete.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepository {



    public List<Student> getAll(){
        List<Student> students = null;
        // mở phiên kêt nối đến cơ sở dữ lieu
        Session session = HibernateUtil.getSession();
        // tao cau lenh truy van
        Query<Student> query = session.createQuery("from Student", Student.class);
        // lay ket qua truy van
        students = query.getResultList();
        // dong ket noi
        session.close();
        return students;
    }


    public void delete(Integer deletedId){
        Session session = HibernateUtil.getSession();
        // b1 tim doi tuong can xoa
        Query<Student> query = session
                .createQuery(" from Student where id = :id", Student.class);
        query.setParameter("id", deletedId);
        Student deletedStudent = query.getSingleResult();
        // b2 xoa doi  tuong neu ton tai
        if(deletedStudent != null){
            Transaction transaction = session.beginTransaction();
            session.delete(deletedStudent);
            transaction.commit();
        }

        // xoa doi tuong neu ton tai
        session.close();
    }


    public void addStudent(Student student){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        student.setId(null);
        session.save(student);
        transaction.commit();
        session.close();
    }

}
