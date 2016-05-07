package com.hungle.jdbc.msmoneyaccess;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import net.ucanaccess.jdbc.UcanaccessDriver;

public class MnyDriver implements Driver {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MnyDriver.class);

    private final UcanaccessDriver wrappedDriver;

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
