import ennity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness {
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Book WHERE IsDeleted = 0");
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("BookId"));
                    book.setBookName(rs.getString("BookName"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setTotalPages(rs.getInt("TotalPages"));
                    book.setContent(rs.getString("Content"));
                    book.setPublisher(rs.getString("Publisher"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setTypeId(rs.getInt("TypeId"));
                    book.setDeleted(rs.getBoolean("IsDeleted"));
                    books.add(book);
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn dữ liệu: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return books;
    }
    
    public boolean createBook(Book book) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_CreateBook(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                callSt.setString(1, book.getBookName());
                callSt.setString(2, book.getTitle());
                callSt.setString(3, book.getAuthor());
                callSt.setInt(4, book.getTotalPages());
                callSt.setString(5, book.getContent());
                callSt.setString(6, book.getPublisher());
                callSt.setDouble(7, book.getPrice());
                callSt.setInt(8, book.getTypeId());
                callSt.setBoolean(9, book.isDeleted());
                
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi tạo mới sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public boolean updateBook(Book book) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_UpdateBook(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                callSt.setInt(1, book.getBookId());
                callSt.setString(2, book.getBookName());
                callSt.setString(3, book.getTitle());
                callSt.setString(4, book.getAuthor());
                callSt.setInt(5, book.getTotalPages());
                callSt.setString(6, book.getContent());
                callSt.setString(7, book.getPublisher());
                callSt.setDouble(8, book.getPrice());
                callSt.setInt(9, book.getTypeId());
                callSt.setBoolean(10, book.isDeleted());
                
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi cập nhật sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public boolean deleteBook(int bookId) {
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_DeleteBook(?)}");
                callSt.setInt(1, bookId);
                int result = callSt.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Lỗi xóa sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return false;
    }
    
    public List<Book> sortBooksByPriceDesc() {
        List<Book> books = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Book WHERE IsDeleted = 0 ORDER BY Price DESC");
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("BookId"));
                    book.setBookName(rs.getString("BookName"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setTotalPages(rs.getInt("TotalPages"));
                    book.setContent(rs.getString("Content"));
                    book.setPublisher(rs.getString("Publisher"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setTypeId(rs.getInt("TypeId"));
                    book.setDeleted(rs.getBoolean("IsDeleted"));
                    books.add(book);
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn dữ liệu: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return books;
    }
    
    public List<Book> searchBookByNameOrContent(String keyword) {
        List<Book> books = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                CallableStatement callSt = conn.prepareCall("{call sp_SearchBookByNameOrContent(?)}");
                callSt.setString(1, "%" + keyword + "%");
                ResultSet rs = callSt.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("BookId"));
                    book.setBookName(rs.getString("BookName"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setTotalPages(rs.getInt("TotalPages"));
                    book.setContent(rs.getString("Content"));
                    book.setPublisher(rs.getString("Publisher"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setTypeId(rs.getInt("TypeId"));
                    book.setDeleted(rs.getBoolean("IsDeleted"));
                    books.add(book);
                }
            } catch (SQLException e) {
                System.out.println("Lỗi tìm kiếm sách: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return books;
    }
    
    public List<Book> groupBooksByPageCount() {
        List<Book> books = new ArrayList<>();
        Connection conn = ConnectionDB.openConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Book WHERE IsDeleted = 0");
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("BookId"));
                    book.setBookName(rs.getString("BookName"));
                    book.setTitle(rs.getString("Title"));
                    book.setAuthor(rs.getString("Author"));
                    book.setTotalPages(rs.getInt("TotalPages"));
                    book.setContent(rs.getString("Content"));
                    book.setPublisher(rs.getString("Publisher"));
                    book.setPrice(rs.getDouble("Price"));
                    book.setTypeId(rs.getInt("TypeId"));
                    book.setDeleted(rs.getBoolean("IsDeleted"));
                    books.add(book);
                }
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Lỗi truy vấn dữ liệu: " + e.getMessage());
            } finally {
                ConnectionDB.closeConnection(conn, null);
            }
        }
        return books;
    }
}