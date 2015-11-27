/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.woofenterprise.dogs.service;

import java.util.Date;

/**
 * Service for getting needed dates.
 * 
 * @author michal.babel@embedit.cz
 */
public interface DateService {
    
    /**
     * Retrieves "today's" midnight.
     * @return "today's"  midnight
     */
    public Date getToday();
    
}
