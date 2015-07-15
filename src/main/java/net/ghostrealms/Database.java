package net.ghostrealms;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
  
  enum SQL {
    H2,
    MYSQL,
    SQLITE;
  }
  
  private final JavaPlugin plugin;
  private final String db;
  private SQL mode = SQL.H2;
  
  private Connection connection;
  
  private String mysql_user;
  private String mysql_pass;
  private String mysql_host;
  private String mysql_database;
  private int    mysql_port;
  
  private ArrayList<String> updateQueue = new ArrayList<String>();
  
  public Database(String db, JavaPlugin plugin) {
    this.db = db;
    this.plugin = plugin;
    
    if(mode == SQL.MYSQL) {
      FileConfiguration config = plugin.getConfig();
      mysql_database = config.getString("database.name");
      mysql_host     = config.getString("database.host");
      mysql_user     = config.getString("database.user");
      mysql_pass     = config.getString("database.pass");
      mysql_port     = config.getInt("database.port");
    }
    
    setupConnection();
  }
  
  private void setupConnection() {
    
    switch(mode) {
      default:
      case H2:
        try {
          Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }

        String url = "jdbc:h2:" + plugin.getDataFolder() + File.separator + db;

        try {
          connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        break;
      case MYSQL:
        try {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        
        try {
          connection = DriverManager.getConnection("jdbc:mysql://" + mysql_host + ":" + mysql_port + 
                                                   "/" + mysql_database,  mysql_user, mysql_pass);
        } catch (SQLException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        break;
      case SQLITE:
        try {
          Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        
        try {
          connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() +
                                                   File.separator + db);
        } catch (SQLException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        break;
    }

  }
  
  protected String getDatabase() {
    return db;
  }
  
  private String getDatabaseURL() {
    return "jdbc:h2:" + plugin.getDataFolder() + File.separator + db;
  }
  
  public void close() {
    try {
      if(!connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * This will add an Update SQL statement to the queue to be updated upon calling #runQueue* 
   * @param statement
   */
  public void queue(String statement) {
    updateQueue.add(statement);
  }
  
  public void runQueue() {
    for(String sql : updateQueue) {
      update(sql);
      updateQueue.remove(sql);
    }
  }
  
  public boolean execute(String sql) {
    try {
      Statement stmt = connection.createStatement();
      boolean status = stmt.execute(sql);
      stmt.closeOnCompletion();
      return status;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }
  
  public int executeUpdate(String sql) {
    try {
      Statement stmt = connection.createStatement();
      int status = stmt.executeUpdate(sql);
      stmt.closeOnCompletion();
      return status;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return -1;
    }
  }
  
  public ResultSet executeQuery(String sql) {
    try {
      Statement stmt = connection.createStatement();
      ResultSet status = stmt.executeQuery(sql);
      stmt.closeOnCompletion();
      return status;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }
  
  public ResultSet query(String sql) {
    return executeQuery(sql);
  }
  
  public int update(String sql) {
    return executeUpdate(sql);
  }
  
  protected void setMode(SQL mode) {
    this.mode = mode;
  }
  
  protected SQL getMode() {
    return mode;
  }

}