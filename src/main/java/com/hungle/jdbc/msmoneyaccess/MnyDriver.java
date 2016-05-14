package com.hungle.jdbc.msmoneyaccess;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import net.ucanaccess.jdbc.UcanaccessDriver;

public class MnyDriver implements Driver {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MnyDriver.class);

    private static final String JACKCESSOPENER = "jackcessopener";

    private final UcanaccessDriver wrappedDriver;

    static {
        try {
            DriverManager.registerDriver(new MnyDriver());
            Class.forName(UcanaccessDriver.class.getCanonicalName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public MnyDriver() {
        super();
        wrappedDriver = new UcanaccessDriver();
    }

    public Connection connect(String url, Properties info) throws SQLException {
        LOGGER.info("> connect");
        LOGGER.info("url=" + url);
        for (Object key : info.keySet()) {
            Object value = info.get(key);
            LOGGER.info("  key=" + key + ", value=" + value);
        }
        boolean useCustomOpener = info.containsKey(JACKCESSOPENER);
        String openerClassName = null;
        if (!useCustomOpener) {
            openerClassName = JEOpener.class.getCanonicalName();
            info.setProperty(JACKCESSOPENER, openerClassName);
        } else {
            openerClassName = info.getProperty(JACKCESSOPENER);
        }
        LOGGER.info(JACKCESSOPENER + "=" + openerClassName);
        
        return wrappedDriver.connect(url, info);
    }

    public boolean acceptsURL(String url) throws SQLException {
        LOGGER.info("> acceptsURL, url=" + url);

        return wrappedDriver.acceptsURL(url);
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        LOGGER.info("> getPropertyInfo, url=" + url);
        for (Object key : info.keySet()) {
            Object value = info.get(key);
            LOGGER.info("  key=" + key + ", value=" + value);
        }
        return wrappedDriver.getPropertyInfo(url, info);
    }

    public int getMajorVersion() {
        LOGGER.info("> getMajorVersion");

        return wrappedDriver.getMajorVersion();
    }

    public int getMinorVersion() {
        LOGGER.info("> getMinorVersion");

        return wrappedDriver.getMinorVersion();
    }

    public boolean jdbcCompliant() {
        LOGGER.info("> jdbcCompliant");

        return wrappedDriver.jdbcCompliant();
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        LOGGER.info("> getParentLogger");

        return wrappedDriver.getParentLogger();
    }
}
