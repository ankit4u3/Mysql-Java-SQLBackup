/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package site.ii.backup.man;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author developeracer
 */
public class PoolDataSourceRegistration {

    private static PoolDataSourceRegistration datasource;
    private ComboPooledDataSource cpds;

    PoolDataSourceRegistration() throws IOException, SQLException, PropertyVetoException {
        try {
            cpds = new ComboPooledDataSource();

            cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
            cpds.setJdbcUrl("jdbc:mysql://" + "122.176.71.171" + "/registration?");

            cpds.setUser("root");
            cpds.setPassword("v721PL7y");

            // the settings below are optional -- c3p0 can work with defaults
            cpds.setMinPoolSize(10);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(50);
            cpds.setMaxStatements(180);
            cpds.setAcquireRetryAttempts(20);
            cpds.setAcquireRetryDelay(100);

        } catch (Exception e) {
            try {
                datasource = new PoolDataSourceRegistration();
            } catch (PropertyVetoException ex) {

            }
        }

    }

    public static PoolDataSourceRegistration getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new PoolDataSourceRegistration();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}