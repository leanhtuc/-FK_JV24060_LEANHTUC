package presentation;

public class BookManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static BookTypeBusiness bookTypeBusiness = new BookTypeBusiness();
    private static BookBusiness bookBusiness = new BookBusiness();
    
    public static void main(String[] args) {
        int choice;
        
        do {
            System.out.println("\nBOOK-MANAGEMENT");
            System.out.println("1. Quản lý loại sách");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (choice) {
                case 1:
                    bookTypeMenu();
                    break;
                case 2:
                    bookMenu();
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 3);
    }
    
    private static void bookTypeMenu() {
        int choice;
        
        do {
            System.out.println("\nBOOKTYPE-MENU");
            System.out.println("1. Danh sách loại sách");
            System.out.println("2. Tạo mới loại sách");
            System.out.println("3. Cập nhật thông tin loại sách");
            System.out.println("4. Xóa loại sách");
            System.out.println("5. Thống kê số lượng sách theo mã loại sách");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (choice) {
                case 1:
                    displayAllBookTypes();
                    break;
                case 2:
                    createBookType();
                    break;
                case 3:
                    updateBookType();
                    break;
                case 4:
                    deleteBookType();
                    break;
                case 5:
                    countBooksByTypeId();
                    break;
                case 6:
                    System.out.println("Trở về menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 6);
    }
    
    private static void displayAllBookTypes() {
        List<BookType> bookTypes = bookTypeBusiness.getAllBookTypes();
        if (bookTypes.isEmpty()) {
            System.out.println("Không có loại sách nào.");
        } else {
            System.out.println("\nDanh sách loại sách:");
            for (BookType bookType : bookTypes) {
                bookType.displayData();
                System.out.println("------------------");
            }
        }
    }
    
    private static void createBookType() {
        BookType bookType = new BookType();
        bookType.inputData(scanner);
        if (bookTypeBusiness.createBookType(bookType)) {
            System.out.println("Tạo mới loại sách thành công.");
        } else {
            System.out.println("Tạo mới loại sách thất bại.");
        }
    }
    
    private static void updateBookType() {
        System.out.print("Nhập mã loại sách cần cập nhật: ");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        
        BookType bookType = findBookTypeById(typeId);
        if (bookType == null) {
            System.out.println("Không tìm thấy loại sách có mã " + typeId);
        } else {
            bookType.displayData();
            System.out.println("\nChọn thông tin cần cập nhật:");
            System.out.println("1. Tên loại sách");
            System.out.println("2. Mô tả");
            System.out.print("Lựa chọn của bạn: ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (updateChoice) {
                case 1:
                    System.out.print("Nhập tên loại sách mới: ");
                    bookType.setTypeName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nhập mô tả mới: ");
                    bookType.setDescription(scanner.nextLine());
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    return;
            }
            
            if (bookTypeBusiness.updateBookType(bookType)) {
                System.out.println("Cập nhật thông tin loại sách thành công.");
            } else {
                System.out.println("Cập nhật thông tin loại sách thất bại.");
            }
        }
    }
    
    private static void deleteBookType() {
        System.out.print("Nhập mã loại sách cần xóa: ");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        
        BookType bookType = findBookTypeById(typeId);
        if (bookType == null) {
            System.out.println("Không tìm thấy loại sách có mã " + typeId);
        } else {
            if (bookTypeBusiness.deleteBookType(typeId)) {
                System.out.println("Xóa loại sách thành công.");
            } else {
                System.out.println("Xóa loại sách thất bại.");
            }
        }
    }
    
    private static void countBooksByTypeId() {
        System.out.print("Nhập mã loại sách: ");
        int typeId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        
        int count = bookTypeBusiness.countBooksByTypeId(typeId);
        System.out.println("Số lượng sách thuộc loại sách có mã " + typeId + " là: " + count);
    }
    
    private static BookType findBookTypeById(int typeId) {
        List<BookType> bookTypes = bookTypeBusiness.getAllBookTypes();
        for (BookType bookType : bookTypes) {
            if (bookType.getTypeId() == typeId) {
                return bookType;
            }
        }
        return null;
    }
    
    private static void bookMenu() {
        int choice;
        
        do {
            System.out.println("\nBOOK-MENU");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Tạo mới sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Hiển thị danh sách các cuốn sách theo giá giảm dần");
            System.out.println("6. Tìm kiếm sách theo tên hoặc nội dung");
            System.out.println("7. Thống kê số lượng sách theo nhóm");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    createBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    sortBooksByPriceDesc();
                    break;
                case 6:
                    searchBookByNameOrContent();
                    break;
                case 7:
                    groupBooksByPageCount();
                    break;
                case 8:
                    System.out.println("Trở về menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 8);
    }
    
    private static void displayAllBooks() {
        List<Book> books = bookBusiness.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Không có sách nào.");
        } else {
            System.out.println("\nDanh sách sách:");
            for (Book book : books) {
                book.displayData();
                System.out.println("------------------");
            }
        }
    }
    
    private static void createBook() {
        Book book = new Book();
        book.inputData(scanner);
        if (bookBusiness.createBook(book)) {
            System.out.println("Tạo mới sách thành công.");
        } else {
            System.out.println("Tạo mới sách thất bại.");
        }
    }
    
    private static void updateBook() {
        System.out.print("Nhập mã sách cần cập nhật: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Không tìm thấy sách có mã " + bookId);
        } else {
            book.displayData();
            System.out.println("\nChọn thông tin cần cập nhật:");
            System.out.println("1. Tên sách");
            System.out.println("2. Tiêu đề");
            System.out.println("3. Tác giả");
            System.out.println("4. Số trang");
            System.out.println("5. Nội dung");
            System.out.println("6. Nhà xuất bản");
            System.out.println("7. Giá");
            System.out.println("8. Mã loại sách");
            System.out.print("Lựa chọn của bạn: ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (updateChoice) {
                case 1:
                    System.out.print("Nhập tên sách mới: ");
                    book.setBookName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nhập tiêu đề mới: ");
                    book.setTitle(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nhập tác giả mới: ");
                    book.setAuthor(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Nhập số trang mới: ");
                    book.setTotalPages(scanner.nextInt());
                    scanner.nextLine(); // Đọc ký tự xuống dòng
                    break;
                case 5:
                    System.out.print("Nhập nội dung mới: ");
                    book.setContent(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Nhập nhà xuất bản mới: ");
                    book.setPublisher(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Nhập giá mới: ");
                    book.setPrice(scanner.nextDouble());
                    scanner.nextLine(); // Đọc ký tự xuống dòng
                    break;
                case 8:
                    System.out.print("Nhập mã loại sách mới: ");
                    book.setTypeId(scanner.nextInt());
                    scanner.nextLine(); // Đọc ký tự xuống dòng
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    return;
            }
            
            if (bookBusiness.updateBook(book)) {
                System.out.println("Cập nhật thông tin sách thành công.");
            } else {
                System.out.println("Cập nhật thông tin sách thất bại.");
            }
        }
    }
    
    private static void deleteBook() {
        System.out.print("Nhập mã sách cần xóa: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng
        
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Không tìm thấy sách có mã " + bookId);
        } else {
            if (bookBusiness.deleteBook(bookId)) {
                System.out.println("Xóa sách thành công.");
            } else {
                System.out.println("Xóa sách thất bại.");
            }
        }
    }
    
    private static void sortBooksByPriceDesc() {
        List<Book> books = bookBusiness.sortBooksByPriceDesc();
        if (books.isEmpty()) {
            System.out.println("Không có sách nào.");
        } else {
            System.out.println("\nDanh sách sách theo giá giảm dần:");
            for (Book book : books) {
                book.displayData();
                System.out.println("------------------");
            }
        }
    }
    
    private static void searchBookByNameOrContent() {
        System.out.print("Nhập từ khóa tìm kiếm (tên hoặc nội dung): ");
        String keyword = scanner.nextLine();
        
        List<Book> books = bookBusiness.searchBookByNameOrContent(keyword);
        if (books.isEmpty()) {
            System.out.println("Không tìm thấy sách nào phù hợp.");
        } else {
            System.out.println("\nKết quả tìm kiếm:");
            for (Book book : books) {
                book.displayData();
                System.out.println("------------------");
            }
        }
    }
    
    private static void groupBooksByPageCount() {
        List<Book> books = bookBusiness.groupBooksByPageCount();
        if (books.isEmpty()) {
            System.out.println("Không có sách nào.");
        } else {
            System.out.println("\nDanh sách sách theo nhóm số trang:");
            for (Book book : books) {
                if (book.getTotalPages() < 50) {
                    System.out.println("Nhóm 1 (<50 trang):");
                } else if (book.getTotalPages() >= 50 && book.getTotalPages() < 300) {
                    System.out.println("Nhóm 2 (50-300 trang):");
                } else {
                    System.out.println("Nhóm(>=300 trang):");
                }
                book.displayData();
                System.out.println("------------------");
            }
        }
    }
    
    private static Book findBookById(int bookId) {
        List<Book> books = bookBusiness.getAllBooks();
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
