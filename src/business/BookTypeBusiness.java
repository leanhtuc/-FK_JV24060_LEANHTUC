import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeBusiness {
    public List<BookType> getAllBookTypes() {
        List<BookType> bookTypes = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM BookType WHERE IsDeleted = 0");
                while (rs.next()) {
                    BookType bookType = new BookType();
                    bookType.setTypeId(rs.getInt("TypeId"));
                    bookType.setTypeName(rs.getString("TypeName"));
                    bookType.setDescription(rs.getString("Description"));
                    bookType.setDeleted(rs.getBoolean("IsDeleted"));
                    bookTypes.add(bookType);
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn dữ liệu: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return bookTypes;
    }
    
    public boolean createBookType(BookType bookType) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_CreateBookType(?, ?)}");
                callSt.setString(1, bookType.getTypeName());
                callSt.setString(2, bookType.getDescription());
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi tạo mới loại sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public boolean updateBookType(BookType bookType) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_UpdateBookType(?, ?, ?)}");
                callSt.setInt(1, bookType.getTypeId());
                callSt.setString(2, bookType.getTypeName());
                callSt.setString(3, bookType.getDescription());
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi cập nhật loại sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public boolean deleteBookType(int typeId) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_DeleteBookType(?)}");
                callSt.setInt(1, typeId);
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi xóa loại sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public int countBooksByTypeId(int typeId) {
        int count = 0;
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_CountBooksByTypeId(?)}");
                callSt.setInt(1, typeId);
                ResultSet rs = callSt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thống kê số lượng sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return count;
    }
}