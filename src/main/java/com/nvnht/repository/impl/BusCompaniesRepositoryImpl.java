/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nvnht.repository.impl;

import com.nvnht.pojo.Buscompanies;
import com.nvnht.repository.BusCompaniesRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nghia
 */
@Repository
@Transactional
public class BusCompaniesRepositoryImpl implements BusCompaniesRepository{
    @Autowired
    private LocalSessionFactoryBean F;
    @Override
    public List<Buscompanies> getBusCompanies() {
        Session s = this.F.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Buscompanies");
        return q.getResultList();
    }

    @Override
    public Buscompanies getBusCompanyById(int id) {
        Session s = this.F.getObject().getCurrentSession();
        return s.get(Buscompanies.class, id);
    }

    @Override
    public boolean update(int id) {
        Session s = this.F.getObject().getCurrentSession();
        Buscompanies b = this.getBusCompanyById(id);
        int state = b.getActive();
        if(state != 0)
            b.setActive(Short.valueOf("0"));
        else
            b.setActive(Short.valueOf("1"));
        s.update(b);
        return true;
    }
    
}
