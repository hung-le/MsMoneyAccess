package com.hungle.jdbc.msmoneyaccess;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import net.ucanaccess.jdbc.JackcessOpenerInterface;

public class JEOpener implements JackcessOpenerInterface {

    public Database open(File file, String password) throws IOException {
        DatabaseBuilder dbd = new DatabaseBuilder(file);
        dbd.setAutoSync(false);
        dbd.setCodecProvider(new CryptCodecProvider(password));
        dbd.setReadOnly(false);
        return dbd.open();
    }

}
