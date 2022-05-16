create or replace and compile java source named LongUtils as
import java.io.*;
import java.sql.*;
import oracle.jdbc.*;

public class LongUtils {

    private static String validChars = "ABCDEFGHIJKLMNOPQRSTUVXYWZabcdefghijklmnopqrstuvxywz#$_";

    private static boolean hasOnlyValidChr(String s) {
        for (int i = 0; i < s.length(); i++)
            if (validChars.indexOf(s.charAt(i)) == -1)
                return false;
        return true;
    }

    private static void validateStmtParameters(String owner, String table, String column) {
        if(owner != null) if (!hasOnlyValidChr(owner)) throw new RuntimeException("Owner has invalid characters!");
        if (!hasOnlyValidChr(table)) throw new RuntimeException("Table name has invalid characters!");
        if (!hasOnlyValidChr(column)) throw new RuntimeException("Column name has invalid characters!");
    }

    private static String createSelectStmt(String owner, String table, String column) {
        validateStmtParameters(owner, table, column);
        if (owner == null) {
            return "select " + column + " from " + table + " where rowid = ?";
        } else {
            return "select " + column + " from " + owner + "." + table + " where rowid = ?";
        }
    }

    private static String createUpdateStmt(String owner, String table, String column) {
        validateStmtParameters(owner, table, column);
        if (owner == null) {
            return "update " + table + " set " + column + " = ? where rowid = ?";
        } else {
            return "update " + owner + "." + table + " set " + column + " = ? where rowid = ?";
        }
    }

    private static int abs(int v) {
        return v < 0 ? -v : v;
    }

    public static void toLong(String sourceOwner,
                              String sourceTable,
                              String sourceColumn,
                              oracle.sql.ROWID sourceRowId,
                              String destOwner,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        Connection conn = new OracleDriver().defaultConnection();
        String username = conn.getMetaData().getUserName();
        PreparedStatement stmtSelect = conn.prepareStatement(createSelectStmt(sourceOwner, sourceTable, sourceColumn));
        PreparedStatement stmtUpdate = conn.prepareStatement(createUpdateStmt(destOwner, destTable, destColumn));
        ((OraclePreparedStatement) stmtSelect).setROWID(1, sourceRowId);
        ResultSet rs = stmtSelect.executeQuery();
        rs.next();
        if (rs.getMetaData().getColumnType(1) == java.sql.Types.CLOB ||
            rs.getMetaData().getColumnType(1) == java.sql.Types.LONGVARCHAR) {
            stmtUpdate.setString(1, rs.getString(1));
        } else {
            stmtUpdate.setBytes(1, rs.getBytes(1));
        }
        ((OraclePreparedStatement) stmtUpdate).setROWID(2, destRowId);
        stmtUpdate.execute();
        stmtUpdate.close();
        stmtSelect.close();
    }

    public static void toLong(String sourceTable,
                              String sourceColumn,
                              oracle.sql.ROWID sourceRowId,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        toLong(null,sourceTable,sourceColumn,sourceRowId,null,destTable,destColumn,destRowId);
    }
                              
    public static void toLong(java.sql.Clob sourceCLOB,
                              String destOwner,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        Connection conn = new OracleDriver().defaultConnection();
        PreparedStatement stmtUpdate = conn.prepareStatement(createUpdateStmt(destOwner, destTable, destColumn));
        if (sourceCLOB != null) {
            stmtUpdate.setString(1, sourceCLOB.getSubString(1, (int) sourceCLOB.length()));
        } else {
            stmtUpdate.setString(1, null);
        }
        ((OraclePreparedStatement) stmtUpdate).setROWID(2, destRowId);
        stmtUpdate.execute();
        stmtUpdate.close();
    }

    public static void toLong(java.sql.Clob sourceCLOB,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        toLong(sourceCLOB, null, destTable, destColumn, destRowId);
    }

    public static void toLong(java.sql.Blob sourceBLOB,
                              String destOwner,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        Connection conn = new OracleDriver().defaultConnection();
        PreparedStatement stmtUpdate = conn.prepareStatement(createUpdateStmt(destOwner, destTable, destColumn));
        if (sourceBLOB != null) {
            stmtUpdate.setBytes(1, sourceBLOB.getBytes(1, (int) sourceBLOB.length()));
        } else {
            stmtUpdate.setBytes(1, null);
        }
        ((OraclePreparedStatement) stmtUpdate).setROWID(2, destRowId);
        stmtUpdate.execute();
        stmtUpdate.close();
    }

    public static void toLong(java.sql.Blob sourceBLOB,
                              String destTable,
                              String destColumn,
                              oracle.sql.ROWID destRowId) throws SQLException, IOException {
        toLong(sourceBLOB, null, destTable, destColumn, destRowId);
    }


    public static String getSubString(String owner,
                                      String table,
                                      String column,
                                      oracle.sql.ROWID rowid,
                                      int position,
                                      int length) throws SQLException, IOException {
        if (length <= 0) return null;
        Connection conn = new OracleDriver().defaultConnection();
        PreparedStatement stmtSelect = conn.prepareStatement(createSelectStmt(owner, table, column));
        String str;
        ((OraclePreparedStatement) stmtSelect).setROWID(1, rowid);
        ResultSet rs = stmtSelect.executeQuery();
        rs.next();
        if (rs.getMetaData().getColumnType(1) != java.sql.Types.LONGVARCHAR) {
            stmtSelect.close();
            return null;
        }
        str = rs.getString(1);
        stmtSelect.close();
        if (abs(position) > str.length()) return null;
        if (position >= 1) position--;
        if (position < 0) position = str.length() - abs(position);
        if (length > (str.length() - position)) length = str.length() - position;
        return str.substring(position, position + length);
    }

    public static String getSubString(String table,
                                      String column,
                                      oracle.sql.ROWID rowid,
                                      int position,
                                      int length) throws SQLException, IOException {
        return getSubString(null, table, column, rowid, position, length);
    }

    public static String getSubString(String owner,
                                      String table,
                                      String column,
                                      oracle.sql.ROWID rowid,
                                      int position) throws SQLException, IOException {
        return getSubString(owner, table, column, rowid, position, 2147483647);
    }

    public static String getSubString(String table,
                                      String column,
                                      oracle.sql.ROWID rowid,
                                      int position) throws SQLException, IOException {
        return getSubString(null, table, column, rowid, position, 2147483647);
    }

    public static int getLength(String owner,
                                String table,
                                String column,
                                oracle.sql.ROWID rowid) throws SQLException, IOException {
        Connection conn = new OracleDriver().defaultConnection();
        PreparedStatement stmtSelect = conn.prepareStatement(createSelectStmt(owner, table, column));
        int length = 0;
        String str;
        ((OraclePreparedStatement) stmtSelect).setROWID(1, rowid);
        ResultSet rs = stmtSelect.executeQuery();
        rs.next();
        str = rs.getString(1);
        if (!rs.wasNull()) length = str.length();
        stmtSelect.close();
        return length;
    }

    public static int getLength(String table,
                                String column,
                                oracle.sql.ROWID rowid) throws SQLException, IOException {
        return getLength(null, table, column, rowid);
    }

    public static int getLengthBytes(String owner,
                                     String table,
                                     String column,
                                     oracle.sql.ROWID rowid) throws SQLException, IOException {
        Connection conn = new OracleDriver().defaultConnection();
        PreparedStatement stmtSelect = conn.prepareStatement(createSelectStmt(owner, table, column));
        int length = 0;
        byte[] str = null;
        ((OraclePreparedStatement) stmtSelect).setROWID(1, rowid);
        ResultSet rs = stmtSelect.executeQuery();
        rs.next();
        str = rs.getBytes(1);
        if (!rs.wasNull()) length = str.length;
        stmtSelect.close();
        return length;
    }

    public static int getLengthBytes(String table,
                                     String column,
                                     oracle.sql.ROWID rowid) throws SQLException, IOException {
        return getLengthBytes(null, table, column, rowid);
    }

    public static int getLengthRaw(String owner,
                                   String table,
                                   String column,
                                   oracle.sql.ROWID rowid) throws SQLException, IOException {
        return getLengthBytes(owner, table, column, rowid);
    }

    public static int getLengthRaw(String table,
                                   String column,
                                   oracle.sql.ROWID rowid) throws SQLException, IOException {
        return getLengthBytes(null, table, column, rowid);
    }

}
/
