package com.ysj.myfirstopendemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ARTICAL".
*/
public class ArticalDao extends AbstractDao<Artical, Long> {

    public static final String TABLENAME = "ARTICAL";

    /**
     * Properties of entity Artical.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Author = new Property(1, String.class, "author", false, "AUTHOR");
        public final static Property Price = new Property(2, double.class, "price", false, "PRICE");
        public final static Property Pages = new Property(3, int.class, "pages", false, "PAGES");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
    }


    public ArticalDao(DaoConfig config) {
        super(config);
    }
    
    public ArticalDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ARTICAL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"AUTHOR\" TEXT," + // 1: author
                "\"PRICE\" REAL NOT NULL ," + // 2: price
                "\"PAGES\" INTEGER NOT NULL ," + // 3: pages
                "\"NAME\" TEXT UNIQUE );"); // 4: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ARTICAL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Artical entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
        stmt.bindDouble(3, entity.getPrice());
        stmt.bindLong(4, entity.getPages());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Artical entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
        stmt.bindDouble(3, entity.getPrice());
        stmt.bindLong(4, entity.getPages());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Artical readEntity(Cursor cursor, int offset) {
        Artical entity = new Artical( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // author
            cursor.getDouble(offset + 2), // price
            cursor.getInt(offset + 3), // pages
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Artical entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setAuthor(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.getDouble(offset + 2));
        entity.setPages(cursor.getInt(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Artical entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Artical entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Artical entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
