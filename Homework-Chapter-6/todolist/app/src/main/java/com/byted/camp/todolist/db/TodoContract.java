package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

public final class TodoContract {

    // TODO 1. 定义创建数据库以及的操作
    public static final String CREATE_SQL = "CREATE TABLE" + TodoNote.Name_Table + "("
            + "(" + TodoNote._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TodoNote.Date_Attribute + " INTEGER, "
            + TodoNote.State_Attribute + " INTEGER, "
            + TodoNote.Content_Attribute + " TEXT, "
            + TodoNote.Priority_Attribute + " INTEGER)";

    public static final String ADD_PRIORITY_COLUMN_SQL =
            "ALTER TABLE " + TodoNote.Name_Table + " ADD " + TodoNote.Priority_Attribute + " INTEGER";

    private TodoContract() {
    }

    public static class TodoNote implements BaseColumns {
        // TODO 2.此处定义表名以及列明
        public static final String Name_Table = "note";
        public static final String Date_Attribute = "date";
        public static final String State_Attribute = "state";
        public static final String Content_Attribute = "content";
        public static final String Priority_Attribute = "priority";
    }

}
