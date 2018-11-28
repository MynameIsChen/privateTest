package com.chen.core;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by chenxianglin on 2017/11/6.
 * Class note:
 */

@Database(name = DBFlowDatabase.NAME, version = DBFlowDatabase.VERSION)
public class DBFlowDatabase {
    public static final String NAME = "TestDb";
    public static final int VERSION = 1;

}
