# 📚 BÀI GIẢNG CHI TIẾT: STUDENT MANAGEMENT SYSTEM

## Mục Lục
1. [Kiến Thức Nền Tảng](#kiến-thức-nền-tảng)
2. [Phần 1: Student.java - Lớp Mô Hình Dữ Liệu](#phần-1-studentjava)
3. [Phần 2: StudentManagement.java - Lớp Quản Lý](#phần-2-studentmanagementjava)
4. [Các Khái Niệm OOP Quan Trọng](#các-khái-niệm-oop)
5. [Luồng Hoạt Động Chương Trình](#luồng-hoạt-động)

---

## Kiến Thức Nền Tảng

### 1. Lập Trình Hướng Đối Tượng (OOP)

**OOP có 4 tính chất chính:**
- **Encapsulation (Đóng gói)**: Ẩn dữ liệu bên trong class
- **Inheritance (Kế thừa)**: Tạo class con từ class cha
- **Polymorphism (Đa hình)**: Một phương thức nhiều hành vi
- **Abstraction (Trừu tượng)**: Ẩn chi tiết, chỉ hiện tính năng

**Trong project này ta sử dụng:**
- ✅ Encapsulation (private fields + public methods)
- ✅ Polymorphism (@Override toString, compare)

### 2. Collection Framework

```
Collection (Interface)
    ├── List (Interface)
    │   └── ArrayList (Class) ← Ta dùng này!
    ├── Set (Interface)
    │   └── HashSet (Class) ← Ta dùng này!
    └── Map (Interface)
        └── HashMap (Class) ← Ta dùng này!
```

---

## Phần 1: Student.java

### 📌 Tổng Quan
File này định nghĩa một **class Student** - đại diện cho MỘT bản ghi đăng ký khóa học của sinh viên.

### Dòng 1: Package Declaration
```java
package com.learn;
```
**Giải thích:**
- `package`: Từ khóa khai báo gói (nhóm các class liên quan)
- `com.learn`: Tên gói (quy ước: domain.company.project)
- **Mục đích**: Tổ chức code, tránh trùng tên class

**Ví dụ thực tế:**
```
Giống như địa chỉ nhà:
- package = tên đường
- class = số nhà
→ com.learn.Student = Đường Com.Learn, nhà Student
```

---

### Dòng 3-5: JavaDoc Comment
```java
/**
 * Student class representing a student course registration.
 */
```
**Giải thích:**
- `/** ... */`: Comment đặc biệt cho tài liệu (JavaDoc)
- Khác với `//` (comment 1 dòng) và `/* */` (comment nhiều dòng)
- **Mục đích**: Tạo documentation tự động

---

### Dòng 6: Class Declaration
```java
public class Student {
```
**Phân tích từng phần:**

| Phần | Ý nghĩa |
|------|---------|
| `public` | Access modifier - ai cũng truy cập được |
| `class` | Từ khóa định nghĩa class |
| `Student` | Tên class (viết hoa chữ đầu) |
| `{` | Bắt đầu body của class |

**Các Access Modifier trong Java:**
```java
public    // Ai cũng truy cập được
private   // Chỉ trong class này
protected // Trong package + class con
default   // Trong package (không viết gì)
```

---

### Dòng 7-11: Private Fields (Thuộc Tính)
```java
private int ordinalNumber;
private String id;
private String studentName;
private String semester;
private String courseName;
```

**Giải thích chi tiết:**

#### Tại sao dùng `private`?
```java
// ❌ SAI - Không bảo vệ dữ liệu
public class Student {
    public String id;  // Ai cũng có thể sửa!
}

// Sử dụng:
Student s = new Student();
s.id = "ABC123";     // OK
s.id = "";           // OK nhưng không hợp lệ!
s.id = "hack!!!";    // OK nhưng nguy hiểm!

// ✅ ĐÚNG - Bảo vệ dữ liệu
public class Student {
    private String id;  // Chỉ class này truy cập

    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;  // Validation trước khi gán
        }
    }
}
```

#### Kiểu Dữ Liệu

**1. Primitive Types (Kiểu nguyên thủy)**
```java
private int ordinalNumber;  // Số nguyên: -2^31 đến 2^31-1
```
- Lưu trực tiếp giá trị
- Không phải object
- Không có methods
- Mặc định: 0

**2. Reference Types (Kiểu tham chiếu)**
```java
private String id;  // Tham chiếu đến object String
```
- Lưu địa chỉ của object
- Có methods
- Mặc định: null

**Minh họa:**
```
MEMORY (Bộ nhớ)
┌─────────────────────┐
│ ordinalNumber: 1    │ ← Lưu trực tiếp giá trị
├─────────────────────┤
│ id: 0x1A2B ─────────┼──> "FX00001" (ở vùng nhớ khác)
└─────────────────────┘
```

---

### Dòng 13-19: Constructor (Hàm Khởi Tạo)
```java
public Student(int ordinalNumber, String id, String studentName,
               String semester, String courseName) {
    this.ordinalNumber = ordinalNumber;
    this.id = id;
    this.studentName = studentName;
    this.semester = semester;
    this.courseName = courseName;
}
```

**Giải thích từng phần:**

#### 1. Constructor là gì?
- Phương thức đặc biệt tạo object
- **Tên = tên class**
- Không có kiểu trả về (không có `void`, không có `int`,...)
- Tự động gọi khi `new Student(...)`

#### 2. Tại sao dùng `this`?
```java
// Tình huống:
public Student(int ordinalNumber) {
    ordinalNumber = ordinalNumber;  // ❌ SAI!
    // Cả 2 đều là parameter, không gán được field!
}

// Giải pháp: dùng this
public Student(int ordinalNumber) {
    this.ordinalNumber = ordinalNumber;  // ✅ ĐÚNG!
    // this.ordinalNumber = field của object
    // ordinalNumber = parameter
}
```

**`this` là gì?**
- `this` = con trỏ đến object hiện tại
- `this.ordinalNumber` = field của object này

**Minh họa:**
```java
Student s1 = new Student(1, "FX001", "An", "Fall2022", "Java");
// Trong constructor:
// this.ordinalNumber = 1  → s1.ordinalNumber = 1
// this.id = "FX001"       → s1.id = "FX001"

Student s2 = new Student(2, "FX002", "Binh", "Fall2022", "Java");
// Trong constructor:
// this.ordinalNumber = 2  → s2.ordinalNumber = 2
// this.id = "FX002"       → s2.id = "FX002"
```

---

### Dòng 21-59: Getter và Setter Methods

#### Getter Example (Dòng 21-23):
```java
public int getOrdinalNumber() {
    return ordinalNumber;
}
```

**Phân tích:**
- `public`: Ai cũng gọi được
- `int`: Trả về kiểu int
- `getOrdinalNumber()`: Tên method (quy ước: get + TênField)
- `return ordinalNumber`: Trả về giá trị của field

**Tại sao cần Getter?**
```java
// Không có getter:
public class Student {
    private int ordinalNumber;
}

Student s = new Student();
System.out.println(s.ordinalNumber);  // ❌ LỖI! private không truy cập được

// Có getter:
public class Student {
    private int ordinalNumber;
    public int getOrdinalNumber() {
        return ordinalNumber;
    }
}

Student s = new Student();
System.out.println(s.getOrdinalNumber());  // ✅ OK!
```

#### Setter Example (Dòng 25-27):
```java
public void setOrdinalNumber(int ordinalNumber) {
    this.ordinalNumber = ordinalNumber;
}
```

**Phân tích:**
- `public`: Ai cũng gọi được
- `void`: Không trả về gì
- `setOrdinalNumber(int ordinalNumber)`: Nhận 1 parameter
- `this.ordinalNumber = ordinalNumber`: Gán giá trị mới

**Tại sao cần Setter?**
```java
// Cho phép validation:
public void setId(String id) {
    if (id == null || id.isEmpty()) {
        throw new IllegalArgumentException("ID không được rỗng!");
    }
    if (!id.startsWith("FX")) {
        throw new IllegalArgumentException("ID phải bắt đầu bằng FX!");
    }
    this.id = id;
}
```

**Pattern: Encapsulation**
```
┌──────────────────────────────┐
│   PRIVATE FIELDS             │
│   (Dữ liệu được bảo vệ)      │
│                              │
│   - ordinalNumber            │
│   - id                       │
│   - studentName              │
├──────────────────────────────┤
│   PUBLIC METHODS             │
│   (Interface để truy cập)    │
│                              │
│   + getOrdinalNumber()       │
│   + setOrdinalNumber()       │
│   + getId()                  │
│   + setId()                  │
└──────────────────────────────┘

Bên ngoài chỉ thấy public methods
Không thể truy cập trực tiếp private fields
```

---

### Dòng 61-65: Override toString Method
```java
@Override
public String toString() {
    return String.format("%-5d | %-10s | %-25s | %-15s | %-10s",
        ordinalNumber, id, studentName, semester, courseName);
}
```

#### @Override là gì?
```java
// Trong class Object (cha của mọi class):
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}

// Kết quả mặc định:
Student s = new Student(...);
System.out.println(s);  // com.learn.Student@15db9742
```

**Annotation @Override:**
- Đánh dấu method này ghi đè (override) method từ class cha
- Compiler sẽ kiểm tra xem method cha có tồn tại không
- Giúp phát hiện lỗi chính tả

```java
// Không có @Override
public String tostring() {  // ❌ Viết sai "toString"
    // Compiler không phát hiện lỗi
    // Sẽ tạo method mới, không override!
}

// Có @Override
@Override
public String tostring() {  // ✅ Compiler báo lỗi ngay!
    // Error: method does not override or implement a method from a supertype
}
```

#### String.format() chi tiết:

**Cú pháp:**
```java
String.format("format string", arg1, arg2, ...)
```

**Format Specifiers:**
```java
%-5d   // integer, căn trái, độ rộng 5
%-10s  // string, căn trái, độ rộng 10
%5d    // integer, căn phải, độ rộng 5
%05d   // integer, padding số 0, độ rộng 5
%.2f   // float, 2 chữ số thập phân
```

**Ví dụ chi tiết:**
```java
// Dữ liệu:
int ordinalNumber = 1;
String id = "FX00001";
String studentName = "Nguyen Van A";
String semester = "Fall2022";
String courseName = "Java";

// Format:
String result = String.format("%-5d | %-10s | %-25s | %-15s | %-10s",
    ordinalNumber, id, studentName, semester, courseName);

// Kết quả:
// "1     | FX00001    | Nguyen Van A             | Fall2022        | Java      "
//  ↑5ch   ↑10 chars    ↑25 chars                  ↑15 chars        ↑10 chars
```

**Visualization:**
```
%-5d        : "1    " (5 ký tự, căn trái)
" | "       : " | "
%-10s       : "FX00001   " (10 ký tự, căn trái)
" | "       : " | "
%-25s       : "Nguyen Van A            " (25 ký tự)
" | "       : " | "
%-15s       : "Fall2022       " (15 ký tự)
" | "       : " | "
%-10s       : "Java      " (10 ký tự)
```

---

## Phần 2: StudentManagement.java

### 📌 Tổng Quan
Class này quản lý toàn bộ hệ thống: thêm, tìm, sửa, xóa, báo cáo.

---

### Dòng 3-10: Import Statements
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
```

**Tại sao cần import?**
```java
// Không import:
java.util.ArrayList<String> list = new java.util.ArrayList<>();  // Dài dòng!

// Có import:
import java.util.ArrayList;
ArrayList<String> list = new ArrayList<>();  // Gọn gàng!
```

**Các class được import:**

| Class | Mục đích | Ví dụ |
|-------|----------|-------|
| ArrayList | Danh sách động | `ArrayList<Student> students` |
| Collections | Tiện ích cho Collection | `Collections.sort(list)` |
| Comparator | So sánh object | Sắp xếp theo tên |
| HashMap | Bảng băm (key-value) | `Map<String, StudentSummary>` |
| HashSet | Tập hợp không trùng | Đếm khóa học unique |
| Scanner | Đọc input từ bàn phím | `scanner.nextLine()` |

---

### Dòng 16-19: Instance Variables (Biến Instance)
```java
private ArrayList<Student> students;
private Scanner scanner;
private int ordinalCounter;
private static final String[] VALID_COURSES = {"Java", ".Net", "C/C++"};
```

#### 1. ArrayList<Student>
```java
private ArrayList<Student> students;
```

**ArrayList là gì?**
- Mảng động (tự động tăng kích thước)
- Lưu trữ các object cùng kiểu
- Implements interface List

**So sánh với Array thông thường:**
```java
// Array - Kích thước cố định
Student[] arr = new Student[10];  // Chỉ chứa 10 sinh viên
arr[0] = new Student(...);
// arr[10] = ...;  // ❌ LỖI! Vượt quá kích thước

// ArrayList - Kích thước động
ArrayList<Student> list = new ArrayList<>();  // Không giới hạn
list.add(new Student(...));  // Sinh viên thứ 1
list.add(new Student(...));  // Sinh viên thứ 2
// ... thêm bao nhiêu cũng được!
```

**Generic Type <Student>:**
```java
ArrayList<Student> students;  // Chỉ chứa Student

// Không dùng generic:
ArrayList students = new ArrayList();
students.add(new Student(...));  // OK
students.add("String");          // OK
students.add(123);               // OK
Student s = (Student) students.get(0);  // Phải ép kiểu, dễ lỗi!

// Dùng generic:
ArrayList<Student> students = new ArrayList<>();
students.add(new Student(...));  // OK
students.add("String");          // ❌ LỖI COMPILE!
Student s = students.get(0);     // Không cần ép kiểu
```

**Các method quan trọng:**
```java
ArrayList<Student> list = new ArrayList<>();

// Thêm
list.add(student);              // Thêm vào cuối
list.add(0, student);           // Thêm vào vị trí 0

// Lấy
Student s = list.get(0);        // Lấy phần tử đầu tiên
int size = list.size();         // Số lượng phần tử

// Xóa
list.remove(0);                 // Xóa phần tử đầu
list.remove(student);           // Xóa object cụ thể
list.clear();                   // Xóa tất cả

// Kiểm tra
boolean empty = list.isEmpty(); // Có rỗng không?
boolean has = list.contains(s); // Có chứa s không?
```

#### 2. Scanner
```java
private Scanner scanner;
```

**Scanner là gì?**
- Class để đọc input từ nhiều nguồn
- Trong project: đọc từ bàn phím (System.in)

**Cách hoạt động:**
```java
Scanner scanner = new Scanner(System.in);

// Đọc một dòng
String line = scanner.nextLine();  // Đọc đến khi gặp Enter

// Đọc số nguyên
int number = scanner.nextInt();    // Đọc số, KHÔNG đọc Enter

// ⚠️ Vấn đề thường gặp:
scanner.nextInt();     // Nhập: 5[Enter]  → Đọc 5, còn [Enter]
scanner.nextLine();    // Đọc [Enter] còn lại → String rỗng!

// ✅ Giải pháp trong project này:
// Luôn dùng nextLine() + parseInt()
String input = scanner.nextLine();  // Đọc cả dòng
int number = Integer.parseInt(input);  // Parse thành số
```

#### 3. ordinalCounter
```java
private int ordinalCounter;
```

**Mục đích:** Tạo số thứ tự tự động tăng

```java
// Ban đầu:
ordinalCounter = 1;

// Đăng ký sinh viên 1:
Student s1 = new Student(ordinalCounter++, ...);  // ordinalCounter = 1, sau đó tăng lên 2
// s1.ordinalNumber = 1

// Đăng ký sinh viên 2:
Student s2 = new Student(ordinalCounter++, ...);  // ordinalCounter = 2, sau đó tăng lên 3
// s2.ordinalNumber = 2
```

**ordinalCounter++ vs ++ordinalCounter:**
```java
int x = 5;
int y = x++;  // y = 5, x = 6 (dùng rồi mới tăng)

int a = 5;
int b = ++a;  // b = 6, a = 6 (tăng rồi mới dùng)
```

#### 4. static final Array
```java
private static final String[] VALID_COURSES = {"Java", ".Net", "C/C++"};
```

**Phân tích từng keyword:**

**`static`:**
- Thuộc về class, không thuộc object
- Chỉ có 1 bản copy cho tất cả object

```java
// Không có static:
public class StudentManagement {
    private String[] courses = {"Java", ".Net", "C/C++"};
}

StudentManagement sm1 = new StudentManagement();
StudentManagement sm2 = new StudentManagement();
// Tạo 2 array riêng biệt trong memory! Lãng phí!

// Có static:
public class StudentManagement {
    private static String[] courses = {"Java", ".Net", "C/C++"};
}

StudentManagement sm1 = new StudentManagement();
StudentManagement sm2 = new StudentManagement();
// Chỉ 1 array duy nhất, cả 2 object cùng dùng chung!
```

**`final`:**
- Không thể thay đổi tham chiếu
- Nhưng có thể thay đổi nội dung (nếu là array hoặc object)

```java
final String[] VALID_COURSES = {"Java", ".Net", "C/C++"};

VALID_COURSES = new String[]{"Python"};  // ❌ LỖI! Không đổi được tham chiếu
VALID_COURSES[0] = "Python";             // ✅ OK! Đổi nội dung được

// Muốn không đổi được cả nội dung:
private static final List<String> VALID_COURSES =
    Collections.unmodifiableList(Arrays.asList("Java", ".Net", "C/C++"));
```

**Quy ước đặt tên:**
- `static final` = hằng số → viết HOA_TOÀN_BỘ_VÀ_DÙNG_UNDERSCORE

---

### Dòng 21-25: Constructor
```java
public StudentManagement() {
    this.students = new ArrayList<>();
    this.scanner = new Scanner(System.in);
    this.ordinalCounter = 1;
}
```

**Constructor này khởi tạo 3 thứ:**

1. **ArrayList rỗng:**
```java
this.students = new ArrayList<>();
// Diamond operator <> - Java 7+
// Tương đương: new ArrayList<Student>()
```

2. **Scanner đọc từ bàn phím:**
```java
this.scanner = new Scanner(System.in);
// System.in = Standard Input (bàn phím)
// System.out = Standard Output (màn hình)
// System.err = Standard Error (màn hình, màu đỏ)
```

3. **Counter bắt đầu từ 1:**
```java
this.ordinalCounter = 1;
```

---

### Dòng 27-55: Method run() - Vòng Lặp Chính
```java
public void run() {
    boolean running = true;
    while (running) {
        displayMainMenu();
        int choice = getIntInput("Nhap lua chon cua ban: ");

        switch (choice) {
            case 1: registerStudents(); break;
            case 2: searchAndSort(); break;
            case 3: updateOrDelete(); break;
            case 4: generateReport(); break;
            case 5: running = false; break;
            default: System.out.println("\nLua chon khong hop le!");
        }
    }
    scanner.close();
}
```

#### Flow của method run():

```
START
  │
  ├─> boolean running = true
  │
  ├─> WHILE (running == true)
  │     │
  │     ├─> Hiển thị menu
  │     │
  │     ├─> Nhập lựa chọn
  │     │
  │     ├─> SWITCH (choice)
  │     │     ├─ case 1: Đăng ký sinh viên
  │     │     ├─ case 2: Tìm kiếm
  │     │     ├─ case 3: Cập nhật/Xóa
  │     │     ├─ case 4: Báo cáo
  │     │     ├─ case 5: running = false (thoát vòng lặp)
  │     │     └─ default: Thông báo lỗi
  │     │
  │     └─> Quay lại đầu while
  │
  └─> scanner.close()
END
```

#### While Loop:
```java
while (running) { ... }
// Tương đương:
while (running == true) { ... }
```

**Tại sao dùng boolean running?**
```java
// ❌ Cách 1: Khó đọc
while (true) {
    if (choice == 5) break;  // break ở nhiều nơi, khó maintain
}

// ✅ Cách 2: Dễ đọc, rõ ràng
boolean running = true;
while (running) {
    if (choice == 5) running = false;  // Thay đổi điều kiện
}
```

#### Switch Statement:
```java
switch (choice) {
    case 1:
        registerStudents();
        break;  // Quan trọng! Không có break sẽ chạy tiếp case sau
    case 2:
        searchAndSort();
        break;
    // ...
    default:  // Nếu không khớp case nào
        System.out.println("Lua chon khong hop le!");
}
```

**Tại sao cần `break`?**
```java
int choice = 1;

// Không có break:
switch (choice) {
    case 1:
        System.out.println("Case 1");  // In ra
    case 2:
        System.out.println("Case 2");  // In ra luôn!
    case 3:
        System.out.println("Case 3");  // In ra luôn!
}
// Output: Case 1 \n Case 2 \n Case 3

// Có break:
switch (choice) {
    case 1:
        System.out.println("Case 1");  // In ra
        break;  // Thoát switch
    case 2:
        System.out.println("Case 2");
        break;
}
// Output: Case 1
```

#### scanner.close():
```java
scanner.close();  // Đóng Scanner khi không dùng nữa
```

**Tại sao cần close?**
- Scanner mở một resource (System.in)
- Không close → resource leak (rò rỉ tài nguyên)
- Best practice: luôn close các resource (file, socket, scanner,...)

---

### Dòng 57-67: Method displayMainMenu()
```java
private void displayMainMenu() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("CHAO MUNG DEN QUAN LY DANG KY HOC PHAN CUA SINH VIEN");
    System.out.println("=".repeat(60));
    System.out.println("1. Dang ky hoc phan tung sinh vien");
    System.out.println("2. Tim kiem va Sap xep");
    System.out.println("3. Cap nhat/Xoa");
    System.out.println("4. Bao cao");
    System.out.println("5. Thoat");
    System.out.println("=".repeat(60));
}
```

#### String.repeat() - Java 11+:
```java
"=".repeat(60)
// Kết quả: "============================================================"
//          (60 ký tự '=')

// Tương đương (cách cũ):
String line = "";
for (int i = 0; i < 60; i++) {
    line += "=";
}
```

#### "\n" - Newline Character:
```java
System.out.println("\n" + "=".repeat(60));
// \n = xuống dòng (newline)
// Kết quả: 1 dòng trống + dòng "===="

System.out.println("Hello\nWorld");
// Output:
// Hello
// World
```

---

### Dòng 69-110: Method registerStudents() - QUAN TRỌNG!

Đây là method phức tạp nhất! Phân tích từng phần:

#### Phần 1: Khởi tạo (Dòng 70-72)
```java
System.out.println("\n--- DANG KY HOC PHAN TUNG SINH VIEN ---");
int count = 0;
boolean continueRegistration = true;
```

- `count`: Đếm số sinh viên đã đăng ký
- `continueRegistration`: Flag để điều khiển vòng lặp

#### Phần 2: Vòng lặp chính (Dòng 74-107)
```java
while (continueRegistration) {
    // Logic đăng ký
}
```

**Flow Chart:**
```
START
  │
  ├─> count = 0, continueRegistration = true
  │
  ├─> WHILE (continueRegistration)
  │     │
  │     ├─> IF (count >= 10)
  │     │     ├─> Hỏi: "Bạn có muốn tiếp tục? (Y/N)"
  │     │     ├─> IF (nhập N)
  │     │     │     └─> break (thoát vòng lặp)
  │     │     └─> IF (nhập Y)
  │     │           └─> tiếp tục
  │     │
  │     ├─> Nhập thông tin sinh viên
  │     │     ├─> Mã SV
  │     │     ├─> Tên SV
  │     │     ├─> Học kỳ
  │     │     └─> Khóa học
  │     │
  │     ├─> Validate khóa học
  │     │     └─> IF (không hợp lệ) → continue (bỏ qua, lặp lại)
  │     │
  │     ├─> Check trùng lặp
  │     │     └─> IF (trùng) → continue (bỏ qua, lặp lại)
  │     │
  │     ├─> Tạo object Student
  │     │     └─> students.add(student)
  │     │
  │     ├─> count++
  │     │
  │     └─> Quay lại đầu while
  │
  └─> In "Đã đăng ký thành công X sinh viên"
END
```

#### Chi tiết dòng 75-80: Kiểm tra 10 sinh viên
```java
if (count >= 10) {
    String choice = getStringInput("\nBan da dang ky " + count + " sinh vien. Ban co muon tiep tuc? (Y/N): ");
    if (!choice.equalsIgnoreCase("Y")) {
        break;
    }
}
```

**equalsIgnoreCase():**
```java
String choice = "y";

// So sánh phân biệt hoa thường:
choice.equals("Y")          // false
choice.equals("y")          // true

// So sánh không phân biệt hoa thường:
choice.equalsIgnoreCase("Y")  // true
choice.equalsIgnoreCase("y")  // true
choice.equalsIgnoreCase("YES") // false (khác chuỗi)
```

**Toán tử ! (NOT):**
```java
boolean isY = choice.equalsIgnoreCase("Y");  // true nếu nhập Y/y

if (!isY) {  // NOT true = false, NOT false = true
    break;   // Chỉ break nếu KHÔNG phải Y
}

// Tương đương:
if (choice.equalsIgnoreCase("Y")) {
    // Không làm gì, tiếp tục
} else {
    break;
}
```

#### Chi tiết dòng 84-90: Nhập thông tin
```java
String id = getStringInput("Nhap ma sinh vien: ");
String name = getStringInput("Nhap ten sinh vien: ");
String semester = getStringInput("Nhap hoc ky (VD: Fall2022, Spring2023): ");

System.out.println("Cac khoa hoc kha dung: 1. Java, 2. .Net, 3. C/C++");
int courseChoice = getIntInput("Chon khoa hoc (1-3): ");
String courseName = getCourseFromChoice(courseChoice);
```

**Tại sao dùng method getStringInput()?**
- Tránh lặp code
- Dễ maintain
- Có thể thêm validation chung

#### Chi tiết dòng 92-95: Validate khóa học
```java
if (courseName == null) {
    System.out.println("Lua chon khoa hoc khong hop le!");
    continue;
}
```

**`continue` vs `break`:**
```java
// continue: Bỏ qua phần còn lại, quay lại đầu vòng lặp
while (true) {
    System.out.println("A");
    if (condition) continue;
    System.out.println("B");  // Không chạy nếu continue
    System.out.println("C");  // Không chạy nếu continue
}

// break: Thoát hoàn toàn vòng lặp
while (true) {
    System.out.println("A");
    if (condition) break;
    System.out.println("B");  // Không chạy nếu break
}
System.out.println("After loop");  // Chạy sau break
```

#### Chi tiết dòng 97-101: Check duplicate
```java
if (isDuplicateRegistration(id, semester, courseName)) {
    System.out.println("Sinh vien " + id + " da dang ky khoa hoc " + courseName + " trong ky " + semester + " roi!");
    System.out.println("Khong the dang ky trung khoa hoc trong cung mot ky!");
    continue;
}
```

**Method isDuplicateRegistration()** sẽ giải thích bên dưới.

#### Chi tiết dòng 103-106: Tạo và thêm sinh viên
```java
Student student = new Student(ordinalCounter++, id, name, semester, courseName);
students.add(student);
count++;
System.out.println("Dang ky thanh cong!");
```

**ordinalCounter++:**
```java
// Lần 1:
Student s1 = new Student(ordinalCounter++, ...);
// ordinalCounter = 1 → dùng 1
// sau đó ordinalCounter = 2

// Lần 2:
Student s2 = new Student(ordinalCounter++, ...);
// ordinalCounter = 2 → dùng 2
// sau đó ordinalCounter = 3
```

---

### Dòng 112-121: Method isDuplicateRegistration()
```java
private boolean isDuplicateRegistration(String id, String semester, String courseName) {
    for (Student student : students) {
        if (student.getId().equals(id) &&
            student.getSemester().equals(semester) &&
            student.getCourseName().equals(courseName)) {
            return true;
        }
    }
    return false;
}
```

#### Enhanced For Loop (For-Each):
```java
for (Student student : students) {
    // student = từng phần tử trong students
}

// Tương đương với for thông thường:
for (int i = 0; i < students.size(); i++) {
    Student student = students.get(i);
}
```

**Khi nào dùng for-each?**
```java
// ✅ Dùng for-each khi:
// - Duyệt toàn bộ collection
// - Không cần index
for (Student s : students) {
    System.out.println(s.getName());
}

// ✅ Dùng for thông thường khi:
// - Cần index
// - Cần bỏ qua phần tử
// - Cần duyệt ngược
for (int i = 0; i < students.size(); i++) {
    System.out.println(i + ": " + students.get(i).getName());
}
```

#### Logic kiểm tra trùng:
```java
if (student.getId().equals(id) &&
    student.getSemester().equals(semester) &&
    student.getCourseName().equals(courseName))
```

**Truth Table (Bảng chân lý):**
```
ID match | Semester match | Course match | Result
---------|----------------|--------------|-------
true     | true           | true         | TRÙNG!
true     | true           | false        | OK (khác khóa học)
true     | false          | true         | OK (khác kỳ)
false    | true           | true         | OK (khác sinh viên)
```

**Ví dụ:**
```
Đã có: FX00001 - Fall2022 - Java

Đăng ký mới:
✅ FX00001 - Fall2022 - .Net     → OK (khác khóa học)
✅ FX00001 - Spring2023 - Java   → OK (khác kỳ)
❌ FX00001 - Fall2022 - Java     → TRÙNG!
```

---

### Dòng 123-128: Method getCourseFromChoice()
```java
private String getCourseFromChoice(int choice) {
    if (choice >= 1 && choice <= VALID_COURSES.length) {
        return VALID_COURSES[choice - 1];
    }
    return null;
}
```

#### Array indexing:
```java
String[] VALID_COURSES = {"Java", ".Net", "C/C++"};
//                         ↑0      ↑1      ↑2

// User chọn 1 → lấy index 0 → "Java"
// User chọn 2 → lấy index 1 → ".Net"
// User chọn 3 → lấy index 2 → "C/C++"
```

**Tại sao choice - 1?**
```java
// User interface (1-based):
1. Java
2. .Net
3. C/C++

// Array (0-based):
[0] = "Java"
[1] = ".Net"
[2] = "C/C++"

// Chuyển đổi:
choice = 1 → index = 0 → VALID_COURSES[0] = "Java"
choice = 2 → index = 1 → VALID_COURSES[1] = ".Net"
```

#### Return null:
```java
return null;  // Trả về null nếu choice không hợp lệ
```

**null là gì?**
- Giá trị đặc biệt cho reference type
- Nghĩa: "không trỏ đến object nào"
- Chỉ dùng với object, không dùng với primitive

```java
String s = null;  // OK
int x = null;     // ❌ LỖI! int là primitive
```

---

### Dòng 130-162: Method searchAndSort()
```java
private void searchAndSort() {
    System.out.println("\n--- TIM KIEM VA SAP XEP ---");
    String searchName = getStringInput("Nhap ten sinh vien can tim (co the nhap mot phan): ");

    ArrayList<Student> foundStudents = new ArrayList<>();
    for (Student student : students) {
        if (student.getStudentName().toLowerCase().contains(searchName.toLowerCase())) {
            foundStudents.add(student);
        }
    }

    if (foundStudents.isEmpty()) {
        System.out.println("Khong tim thay sinh vien nao!");
        return;
    }

    Collections.sort(foundStudents, new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
        }
    });

    System.out.println("\nKet qua tim kiem (da sap xep theo ten):");
    System.out.println("-".repeat(80));
    System.out.printf("%-25s | %-15s | %-15s\n", "Ten sinh vien", "Hoc ky", "Khoa hoc");
    System.out.println("-".repeat(80));

    for (Student student : foundStudents) {
        System.out.printf("%-25s | %-15s | %-15s\n",
            student.getStudentName(), student.getSemester(), student.getCourseName());
    }
}
```

#### Phần 1: Tìm kiếm (Dòng 134-139)

**toLowerCase():**
```java
String name = "Nguyen Van A";
String search = "van";

// Không dùng toLowerCase():
name.contains(search)  // false (phân biệt hoa thường)

// Dùng toLowerCase():
name.toLowerCase().contains(search.toLowerCase())
// "nguyen van a".contains("van")
// true ✅
```

**contains():**
```java
String fullName = "Nguyen Van A";

fullName.contains("Nguyen")  // true
fullName.contains("Van")     // true
fullName.contains("nguyen")  // false (phân biệt hoa thường)
fullName.contains("Tran")    // false
```

**Ví dụ tìm kiếm:**
```
Danh sách:
1. Nguyen Van A
2. Tran Van B
3. Le Thi C
4. Nguyen Thi D

Search: "Van"
→ Kết quả: Nguyen Van A, Tran Van B

Search: "nguyen"  (chuyển thành "nguyen")
→ Kết quả: Nguyen Van A, Nguyen Thi D
```

#### Phần 2: Sắp xếp (Dòng 146-151) - QUAN TRỌNG!

**Collections.sort():**
```java
Collections.sort(list);  // Sắp xếp tăng dần (mặc định)
```

**Nhưng sắp xếp theo tiêu chí nào?**
```java
// Với số nguyên: Rõ ràng
ArrayList<Integer> numbers = new ArrayList<>();
Collections.sort(numbers);  // 1, 2, 3, 4, ...

// Với String: Rõ ràng
ArrayList<String> names = new ArrayList<>();
Collections.sort(names);  // A, B, C, ...

// Với Student: Không rõ ràng!
ArrayList<Student> students = new ArrayList<>();
Collections.sort(students);  // Sắp xếp theo gì???
// → Phải chỉ định!
```

**Comparator Interface:**
```java
Collections.sort(foundStudents, new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
    }
});
```

**Phân tích:**

1. **Anonymous Class (Lớp vô danh):**
```java
new Comparator<Student>() {  // Tạo object từ interface
    @Override
    public int compare(Student s1, Student s2) {
        // Logic so sánh
    }
}

// Tương đương:
class MyComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        // Logic so sánh
    }
}
Comparator<Student> comp = new MyComparator();
Collections.sort(foundStudents, comp);

// Nhưng dùng anonymous class ngắn gọn hơn!
```

2. **compare() method:**
```java
public int compare(Student s1, Student s2)
```

**Quy tắc return:**
```
return < 0  →  s1 đứng trước s2
return 0    →  s1 = s2 (thứ tự không đổi)
return > 0  →  s1 đứng sau s2
```

**Ví dụ:**
```java
compare(studentA, studentB)

// Nếu return -1:
// studentA đứng trước studentB
// → [A, B]

// Nếu return 1:
// studentA đứng sau studentB
// → [B, A]
```

3. **compareToIgnoreCase():**
```java
s1.getStudentName().compareToIgnoreCase(s2.getStudentName())

// Ví dụ:
"An".compareToIgnoreCase("Binh")   // -1 (A < B)
"Binh".compareToIgnoreCase("An")   // 1  (B > A)
"An".compareToIgnoreCase("AN")     // 0  (bằng nhau)
```

**Visualization của sorting:**
```
Ban đầu:
[Nguyen Van B, Le Van A, Tran Van C]

Bước 1: Compare("Nguyen Van B", "Le Van A")
→ "Nguyen" > "Le" → return > 0
→ Đổi chỗ: [Le Van A, Nguyen Van B, Tran Van C]

Bước 2: Compare("Nguyen Van B", "Tran Van C")
→ "Nguyen" < "Tran" → return < 0
→ Giữ nguyên: [Le Van A, Nguyen Van B, Tran Van C]

Kết quả:
[Le Van A, Nguyen Van B, Tran Van C]
```

#### Phần 3: Hiển thị kết quả (Dòng 158-161)

**printf():**
```java
System.out.printf("%-25s | %-15s | %-15s\n",
    student.getStudentName(), student.getSemester(), student.getCourseName());
```

Đã giải thích ở phần Student.java.

---

### Dòng 266-280: Method getStringInput() và getIntInput()

#### getStringInput():
```java
private String getStringInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
}
```

**trim():**
```java
String input = "  Hello World  ";
input.trim()  // "Hello World" (xóa khoảng trắng đầu cuối)

// Tại sao cần trim()?
// User nhập: "  FX00001  " (vô tình có space)
// Không trim: id = "  FX00001  "
// Có trim: id = "FX00001" ✅
```

#### getIntInput():
```java
private int getIntInput(String prompt) {
    while (true) {
        try {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so hop le!");
        }
    }
}
```

**Try-Catch Exception Handling:**
```java
try {
    // Code có thể gây lỗi
} catch (ExceptionType e) {
    // Xử lý lỗi
}
```

**Ví dụ:**
```java
String input = "abc";
int number = Integer.parseInt(input);  // ❌ LỖI! "abc" không phải số

// Với try-catch:
try {
    String input = "abc";
    int number = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Lỗi! Không phải số!");
}
// Chương trình không crash, tiếp tục chạy
```

**while (true) + return:**
```java
while (true) {
    try {
        return Integer.parseInt(input);  // Thoát method nếu thành công
    } catch (NumberFormatException e) {
        // Lỗi → tiếp tục vòng lặp → hỏi lại
    }
}
```

**Flow:**
```
User nhập: "abc"
→ parseInt("abc") → NumberFormatException
→ catch → In "Vui long nhap so hop le!"
→ while loop lại
→ Hỏi lại user

User nhập: "123"
→ parseInt("123") → 123
→ return 123 → Thoát method
```

---

### Dòng 237-264: Method generateReport() - QUAN TRỌNG!

```java
private void generateReport() {
    System.out.println("\n--- BAO CAO ---");

    if (students.isEmpty()) {
        System.out.println("Khong co du lieu!");
        return;
    }

    Map<String, StudentSummary> summaryMap = new HashMap<>();

    for (Student student : students) {
        String key = student.getId();
        if (!summaryMap.containsKey(key)) {
            summaryMap.put(key, new StudentSummary(student.getId(), student.getStudentName()));
        }
        summaryMap.get(key).addCourse(student.getCourseName());
    }

    System.out.println("-".repeat(80));
    System.out.printf("%-12s | %-25s | %-25s | %-10s\n",
        "Ma SV", "Ten SV", "Cac khoa hoc", "Tong so");
    System.out.println("-".repeat(80));

    for (StudentSummary summary : summaryMap.values()) {
        System.out.printf("%-12s | %-25s | %-25s | %-10d\n",
            summary.getId(), summary.getName(), summary.getCoursesString(), summary.getTotalCourses());
    }
}
```

#### HashMap - QUAN TRỌNG!

**HashMap là gì?**
- Cấu trúc dữ liệu lưu trữ cặp key-value
- Tìm kiếm cực nhanh (O(1) time complexity)

```java
Map<String, StudentSummary> summaryMap = new HashMap<>();
//   ↑Key type  ↑Value type
```

**Visualization:**
```
HashMap:
┌────────────┬─────────────────────────┐
│    Key     │         Value           │
├────────────┼─────────────────────────┤
│  "FX00001" │ StudentSummary(FX00001) │
│  "FX00002" │ StudentSummary(FX00002) │
│  "FX00003" │ StudentSummary(FX00003) │
└────────────┴─────────────────────────┘
```

**Các method quan trọng:**
```java
// Thêm
map.put(key, value);

// Lấy
Value v = map.get(key);

// Kiểm tra
boolean has = map.containsKey(key);

// Lấy tất cả values
Collection<Value> values = map.values();
```

#### Logic tổng hợp:

**Dữ liệu đầu vào:**
```
students = [
    Student(FX00001, "An", "Fall2022", "Java"),
    Student(FX00001, "An", "Spring2023", "Java"),
    Student(FX00002, "Binh", "Fall2022", ".Net")
]
```

**Quá trình xử lý:**
```java
// Lần 1: Student(FX00001, "An", "Fall2022", "Java")
key = "FX00001"
summaryMap.containsKey("FX00001")  // false
→ Tạo mới: summaryMap.put("FX00001", new StudentSummary("FX00001", "An"))
→ summaryMap.get("FX00001").addCourse("Java")
// summaryMap = {FX00001: courses=[Java]}

// Lần 2: Student(FX00001, "An", "Spring2023", "Java")
key = "FX00001"
summaryMap.containsKey("FX00001")  // true (đã có rồi!)
→ Không tạo mới
→ summaryMap.get("FX00001").addCourse("Java")
// summaryMap = {FX00001: courses=[Java]}  (HashSet tự động loại trùng!)

// Lần 3: Student(FX00002, "Binh", "Fall2022", ".Net")
key = "FX00002"
summaryMap.containsKey("FX00002")  // false
→ Tạo mới: summaryMap.put("FX00002", new StudentSummary("FX00002", "Binh"))
→ summaryMap.get("FX00002").addCourse(".Net")
// summaryMap = {
//     FX00001: courses=[Java],
//     FX00002: courses=[.Net]
// }
```

**Kết quả:**
```
summaryMap = {
    "FX00001" → StudentSummary(id="FX00001", name="An", courses={Java})
    "FX00002" → StudentSummary(id="FX00002", name="Binh", courses={.Net})
}
```

---

### Dòng 283-313: Inner Class StudentSummary

```java
private static class StudentSummary {
    private String id;
    private String name;
    private Set<String> courses;

    public StudentSummary(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new HashSet<>();
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public int getTotalCourses() {
        return courses.size();
    }

    public String getCoursesString() {
        return String.join(", ", courses);
    }
}
```

#### Inner Class (Lớp lồng nhau):
```java
public class Outer {
    private static class Inner {  // Inner class
        // ...
    }
}
```

**Tại sao dùng inner class?**
- Tổ chức code tốt hơn
- Inner class chỉ dùng trong Outer class
- Không cần tạo file riêng

**`static` inner class:**
```java
private static class StudentSummary
```

- Không cần object của outer class
- Có thể tạo instance độc lập

```java
// Static inner class:
StudentSummary summary = new StudentSummary("FX001", "An");  // OK

// Non-static inner class:
class Outer {
    class Inner {}
}
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();  // Phải có outer object!
```

#### HashSet<String> - QUAN TRỌNG!

**HashSet là gì?**
- Tập hợp không chứa phần tử trùng lặp
- Không có thứ tự
- Tìm kiếm nhanh

```java
private Set<String> courses;
this.courses = new HashSet<>();
```

**Ví dụ:**
```java
HashSet<String> set = new HashSet<>();

set.add("Java");    // OK
set.add(".Net");    // OK
set.add("Java");    // Bị bỏ qua (đã có rồi!)

System.out.println(set);  // [Java, .Net]
System.out.println(set.size());  // 2 (không phải 3!)
```

**Tại sao dùng HashSet cho courses?**
```
Sinh viên FX00001 đăng ký:
- Java - Fall2022
- Java - Spring2023
- .Net - Fall2022

→ courses = {"Java", ".Net"}  (loại trùng tự động!)
→ totalCourses = 2
```

#### String.join():
```java
public String getCoursesString() {
    return String.join(", ", courses);
}
```

**String.join() chi tiết:**
```java
Set<String> courses = new HashSet<>();
courses.add("Java");
courses.add(".Net");
courses.add("C/C++");

String.join(", ", courses)
// Kết quả: "Java, .Net, C/C++"
//          (nối các phần tử bằng ", ")

// Tương đương:
String result = "";
for (String course : courses) {
    result += course + ", ";
}
result = result.substring(0, result.length() - 2);  // Xóa ", " cuối
```

---

## Các Khái Niệm OOP

### 1. Encapsulation (Đóng gói)

**Định nghĩa:** Ẩn dữ liệu bên trong class, chỉ cho phép truy cập qua methods.

**Trong project:**
```java
public class Student {
    private String id;  // ẨN dữ liệu

    public String getId() {      // INTERFACE để truy cập
        return id;
    }

    public void setId(String id) {  // INTERFACE để thay đổi
        if (id != null && !id.isEmpty()) {
            this.id = id;  // Có thể validate!
        }
    }
}
```

**Lợi ích:**
- Bảo vệ dữ liệu
- Kiểm soát truy cập
- Dễ maintain

### 2. Polymorphism (Đa hình)

**Định nghĩa:** Một phương thức, nhiều hành vi.

**Trong project:**
```java
// toString() từ class Object
Object obj = new Object();
obj.toString();  // "Object@hashcode"

// toString() override trong Student
Student student = new Student(...);
student.toString();  // "1 | FX00001 | Nguyen Van A | ..."

// Cùng method name, khác hành vi!
```

**Ví dụ khác:**
```java
// compare() trong Comparator
Comparator<Student> byName = new Comparator<Student>() {
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
};

Comparator<Student> byId = new Comparator<Student>() {
    public int compare(Student s1, Student s2) {
        return s1.getId().compareTo(s2.getId());
    }
};

// Cùng method compare(), khác logic!
```

### 3. Abstraction (Trừu tượng)

**Định nghĩa:** Ẩn chi tiết phức tạp, chỉ hiện tính năng.

**Trong project:**
```java
// User chỉ cần biết:
registerStudents();  // Đăng ký sinh viên

// Không cần biết:
// - Validation
// - ArrayList operations
// - Loop logic
// - Exception handling
// Tất cả ẩn bên trong method!
```

### 4. Separation of Concerns

**Định nghĩa:** Mỗi class có trách nhiệm riêng.

**Trong project:**
```
Student class:
- Lưu trữ thông tin sinh viên
- Getter/Setter
- toString()

StudentManagement class:
- Quản lý danh sách
- Logic nghiệp vụ
- User interaction

StudentSummary class:
- Tổng hợp báo cáo
- Đếm khóa học
```

---

## Luồng Hoạt Động Chương Trình

### 1. Khởi động
```
main(String[] args)
  ↓
StudentManagement system = new StudentManagement()
  ↓
Constructor:
  - students = new ArrayList<>()
  - scanner = new Scanner(System.in)
  - ordinalCounter = 1
  ↓
system.run()
```

### 2. Main Loop
```
run()
  ↓
while (running) {
  ↓
  displayMainMenu()
  ↓
  getIntInput("Nhap lua chon: ")
  ↓
  switch (choice) {
    case 1 → registerStudents()
    case 2 → searchAndSort()
    case 3 → updateOrDelete()
    case 4 → generateReport()
    case 5 → running = false
  }
  ↓
  Quay lại while
}
  ↓
scanner.close()
```

### 3. Đăng ký sinh viên (registerStudents)
```
registerStudents()
  ↓
while (continueRegistration) {
  ↓
  if (count >= 10) → Hỏi Y/N
  ↓
  Nhập: id, name, semester, course
  ↓
  getCourseFromChoice(courseChoice)
  ↓
  if (courseName == null) → continue
  ↓
  isDuplicateRegistration(id, semester, courseName)
  ↓
  if (duplicate) → continue
  ↓
  new Student(...)
  ↓
  students.add(student)
  ↓
  count++
}
```

### 4. Báo cáo (generateReport)
```
generateReport()
  ↓
summaryMap = new HashMap<>()
  ↓
for each student in students {
  ↓
  key = student.getId()
  ↓
  if (!summaryMap.containsKey(key)) {
    summaryMap.put(key, new StudentSummary(...))
  }
  ↓
  summaryMap.get(key).addCourse(courseName)
}
  ↓
for each summary in summaryMap.values() {
  ↓
  print summary
}
```

---

## Tổng Kết

### Điểm Mạnh Của Code:

1. **OOP Design tốt:**
   - Encapsulation với private fields
   - Separation of concerns
   - Reusable methods

2. **Data Structures phù hợp:**
   - ArrayList: Danh sách sinh viên
   - HashMap: Tổng hợp nhanh
   - HashSet: Loại trùng tự động

3. **Error Handling:**
   - Try-catch cho input
   - Validation cho duplicate
   - Check null

4. **User Experience:**
   - Menu rõ ràng
   - Thông báo chi tiết
   - Cho phép sửa lỗi

### Có Thể Cải Thiện:

1. **Persistence:** Lưu dữ liệu vào file/database
2. **More Validation:** Email format, ID format
3. **More Features:** Export to CSV, statistics
4. **Testing:** Unit tests

---

## Câu Hỏi Ôn Tập

### Câu 1: Tại sao dùng private cho fields?
<details>
<summary>Xem đáp án</summary>
Để đóng gói dữ liệu, bảo vệ khỏi truy cập trực tiếp, và kiểm soát qua getter/setter.
</details>

### Câu 2: ArrayList khác Array như thế nào?
<details>
<summary>Xem đáp án</summary>
- ArrayList: Kích thước động, nhiều methods tiện ích
- Array: Kích thước cố định, ít tính năng
</details>

### Câu 3: Tại sao dùng HashSet cho courses?
<details>
<summary>Xem đáp án</summary>
HashSet tự động loại bỏ phần tử trùng lặp, giúp đếm số khóa học unique.
</details>

### Câu 4: Giải thích ordinalCounter++
<details>
<summary>Xem đáp án</summary>
Post-increment: Dùng giá trị hiện tại, sau đó tăng lên 1.
Ví dụ: ordinalCounter = 1 → dùng 1, sau đó ordinalCounter = 2
</details>

### Câu 5: continue vs break?
<details>
<summary>Xem đáp án</summary>
- continue: Bỏ qua phần còn lại, quay lại đầu vòng lặp
- break: Thoát hoàn toàn vòng lặp
</details>

---

**🎓 Chúc bạn học tốt!**
