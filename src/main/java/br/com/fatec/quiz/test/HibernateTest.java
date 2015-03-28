/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.quiz.test;

import br.com.fatec.quiz.model.Ranking;
import br.com.fatec.quiz.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author hideki
 */
public class HibernateTest {
    public static void main(String args[]){
        new HibernateTest().rodaTest();
    }

    public void rodaTest() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
//        Ranking ranking = new Ranking();
//        ranking.setNome("Daniel");
//        ranking.setPontuacao(10);
//        session.save(ranking);
//
//        Ranking ranking1 = new Ranking();
//        ranking1.setNome("Mateus");
//        ranking1.setPontuacao(20);
//        session.save(ranking1);

        //session.getTransaction().commit();

        Query q = session.createQuery("From Perguntas ");

        List<Ranking> resultList = q.list();
        System.out.println("num of ranking:" + resultList.size());
        for (Ranking next : resultList) {
            System.out.println("next ranking: " + next);
        }

    }

}
