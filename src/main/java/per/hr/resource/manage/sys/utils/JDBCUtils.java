package per.hr.resource.manage.sys.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    static String url = null;
    static String username = null;
    static String password = null;

    static {
        try {
            //属性文件工具类
            Properties properties = new Properties();
            //标准写法使用类加载器的读取属性文件，后期更正！
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //properties.load(new FileInputStream("src/config/jdbc.properties"));
            String driverClass = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 01-获取数据库连接
     *
     * @return
     */
    private static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    /**
     * 数据库增删改方法
     *
     * @param sql     SQL指令语句
     * @param objects 对应SQL语句中?占位符的对象
     * @return 增删改成功返回true, 失败返回false
     */
    public static boolean dml(String sql, Object... objects) {
        Connection conn = null;
        PreparedStatement pres = null;
        try {
            conn = getConnection();
            pres = conn.prepareStatement(sql);

            for (int i = 0; i < objects.length; i++) {
                pres.setObject(i + 1, objects[i]);
            }

            int i = pres.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pres);
        }
        return false;
    }

    /**
     * 查询单个值的方法 (max/min/sum/avg/count)
     *
     * @param sql
     * @return
     */
    public static Object querySingleValue(String sql) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Object object = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, st, rs);
        }
        return null;
    }

    /**
     * 数据库查询单个数据方法
     *
     * @param sql     对应查询的SQL语句
     * @param c       需要查询的类名
     * @param objects SQL语句对应?占位符所需参数
     * @param <T>     泛型T增加普适性
     * @return 返回T类对象, 包含需要查询的数据
     */
    public static <T> T dqlOne(String sql, Class<T> c, Object... objects) {
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        T obj = null;

        try {
            conn = getConnection();
            pres = conn.prepareStatement(sql);

            for (int i = 0; i < objects.length; i++) {
                pres.setObject(i + 1, objects[i]);
            }

            rs = pres.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();

            if (rs.next()) {
                obj = c.getConstructor().newInstance();
                for (int i = 1; i <= count; i++) {
                    String name = metaData.getColumnName(i);
                    Object value = rs.getObject(name);
                    if (value instanceof Date) {
                        value = String.valueOf(value);
                    }
                    if (value instanceof Timestamp) {
                        value = String.valueOf(value);
                    }
                    createBean(obj, name, value);
                }
            }
            return obj;

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pres, rs);
        }
        return null;
    }

    /**
     * 数据库查询多个数据方法
     *
     * @param <T> 泛型T增加普适性
     * @param sql 对应查询的SQL语句
     * @return 返回T类List集合, 包含需要查询的每个对象的数据
     */
    public static <T> List<T> dqlList(String sql, Class<T> c, Object... objects) {
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        T obj = null;
        List<T> list = new ArrayList<T>();

        try {
            conn = getConnection();
            pres = conn.prepareStatement(sql);

            for (int i = 0; i < objects.length; i++) {
                pres.setObject(i + 1, objects[i]);
            }

            rs = pres.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();

            while (rs.next()) {
                obj = c.getConstructor().newInstance();
                for (int i = 1; i <= count; i++) {
                    String name = metaData.getColumnName(i);
                    Object value = rs.getObject(name);
                    if (value instanceof Date) {
                        value = String.valueOf(value);
                    }
                    if (value instanceof Timestamp) {
                        value = String.valueOf(value);
                    }
                    createBean(obj, name, value);
                }
                list.add(obj);
            }
            return list;

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pres, rs);
        }
        return null;
    }

    /**
     * 07-通用的增删改的方法
     * String sql="update singer set name=?,age=?,sex=? where id=?";
     * Object[] parms={"小A",18,"男",2};
     *
     * @return
     */
    public static boolean update(String sql, Object... parms) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < parms.length; i++) {
                st.setObject(i + 1, parms[i]);
            }
            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, st);
        }
        return false;
    }

    /**
     * 08-查询一条数据
     * String sql="select * from singer where id=?";
     * Object[] parms={5};
     *
     * @param sql
     * @param parms
     * @return
     */
    public static <T> T queryOne(String sql, Class<T> c, Object... parms) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        T object = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < parms.length; i++) {
                st.setObject(i + 1, parms[i]);
            }
            rs = st.executeQuery();
            //结果集元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //查询结果集的列数
            int columnCount = metaData.getColumnCount();
            if (rs.next()) {
                object = c.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //表的字段名 列名
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    if (value instanceof Date) {
                        value = String.valueOf(value);
                    }
                    //System.out.println(columnName+"---"+value);
                    createBean(object, columnName, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, st, rs);
        }
        return object;
    }

    /**
     * 08-查询多条数据
     * String sql="select * from singer where id>?";
     * Object[] parms={2s};
     *
     * @param sql
     * @param parms
     * @return
     */
    public static <T> List<T> queryList(String sql, Class<T> c, Object... parms) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < parms.length; i++) {
                st.setObject(i + 1, parms[i]);
            }
            rs = st.executeQuery();
            //结果集元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //查询结果集的列数
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                T object = c.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //表的字段名 列名
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(columnName);
                    if (value instanceof Date) {
                        value = String.valueOf(value);
                    }
                    //System.out.println(columnName+"---"+value);
                    createBean(object, columnName, value);
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, st, rs);
        }
        return list;
    }

    private static void createBean(Object o, String columnName, Object value) {
        try {
            Class c = o.getClass();
            //属性
            Field field = c.getDeclaredField(columnName);
            //属性类型
            Class type = field.getType();
            field.setAccessible(true);
            field.set(o, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 02-关闭Connection
     *
     * @param conn
     */
    private static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 03-关闭PreparedStatement
     *
     * @param st
     */
    private static void closePreparedStament(PreparedStatement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 04-关闭ResultSet
     *
     * @param rs
     */
    private static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 05-增删改 关闭资源
     *
     * @param conn
     * @param st
     */
    private static void closeConnection(Connection conn, PreparedStatement st) {
        closeConnection(conn);
        closePreparedStament(st);
    }

    /**
     * 06-查询 关闭资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    private static void closeConnection(Connection conn, PreparedStatement st, ResultSet rs) {
        closeConnection(conn);
        closePreparedStament(st);
        closeResultSet(rs);
    }
}
