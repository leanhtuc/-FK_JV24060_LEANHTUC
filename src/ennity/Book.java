package ennity;

import java.util.Scanner;

public class Book implements IBookManagement{
    private int bookId;
    private String bookName;
    private String title;
    private String author;
    private int totalPages;
    private String content;
    private String publisher;
    private double price;
    private int typeId;
    private boolean isDeleted;
    
    public Book() {}
    
    public Book(int bookId, String bookName, String title, String author, int totalPages, String content, String publisher, double price, int typeId, boolean isDeleted) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.content = content;
        this.publisher = publisher;
        this.price = price;
        this.typeId = typeId;
        this.isDeleted = isDeleted;
    }
    
    // Getter và Setter
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getTotalPages() {
        return totalPages;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getTypeId() {
        return typeId;
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    public boolean isDeleted() {
        return isDeleted;
    }
    
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    
    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập tiêu đề sách: ");
        this.title = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        this.author = scanner.nextLine();
        System.out.print("Nhập số trang: ");
        this.totalPages = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        System.out.print("Nhập nội dung: ");
        this.content = scanner.nextLine();
        System.out.print("Nhập nhà xuất bản: ");
        this.publisher = scanner.nextLine();
        System.out.print("Nhập giá sách: ");
        this.price = scanner.nextDouble();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        System.out.print("Nhập mã loại sách: ");
        this.typeId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
    }
    
    @Override
    public void displayData() {
        System.out.println("Mã sách: " + bookId);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tiêu đề: " + title);
        System.out.println("Tác giả: " + author);
        System.out.println("Số trang: " + totalPages);
        System.out.println("Nội dung: " + content);
        System.out.println("Nhà xuất bản: " + publisher);
        System.out.println("Giá: " + price);
        System.out.println("Mã loại sách: " + typeId);
    }
}

