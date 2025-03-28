[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=18688023&assignment_repo_type=AssignmentRepo)

### **1. Basic Entity Mapping**

**Yêu cầu:**  
Tạo một entity `User` với các trường sau:

- `id` (Long) - Khóa chính, tự động sinh
- `name` (String)
- `email` (String) - Unique, không được null

Đảm bảo entity được ánh xạ đúng vào bảng `users`.

---

### **2. Simple One-to-Many Relationship**

**Yêu cầu:**  
Tạo một entity `Library` và một entity `Book`. Một `Library` có thể chứa nhiều `Books` (mối quan hệ one-to-many).
Entity `Library` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `name` (String)

Entity `Book` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `title` (String)
- `author` (String)

Đảm bảo mối quan hệ giữa các entity được ánh xạ chính xác, với khóa ngoại từ `Book` đến `Library`.

---

### **3. Many-to-One Relationship**

**Yêu cầu:**  
Tạo một entity `Product` và một entity `Category`. Một `Product` thuộc về một `Category` (mối quan hệ many-to-one).
Entity `Product` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `productName` (String)
- `category` (Category) - Mối quan hệ many-to-one với `Category`

Entity `Category` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `name` (String)

Đảm bảo entity `Product` có khóa ngoại trỏ đến `Category`.

---

### **4. One-to-One Relationship**

**Yêu cầu:**  
Tạo một entity `UserProfile` và một entity `User`, trong đó mỗi `User` có một `UserProfile` (mối quan hệ one-to-one).
Entity `User` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `name` (String)

Entity `UserProfile` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `bio` (String)
- `user` (User) - Mối quan hệ one-to-one với `User`

Đảm bảo mối quan hệ được ánh xạ chính xác với khóa ngoại trong bảng `UserProfile`.

---

### **5. Many-to-Many Relationship**

**Yêu cầu:**  
Tạo một entity `Student` và một entity `Course`, trong đó một `Student` có thể tham gia nhiều `Courses`, và một `Course`
có thể có nhiều `Students` (mối quan hệ many-to-many). Entity `Student` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `name` (String)

Entity `Course` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `courseName` (String)

Đảm bảo mối quan hệ được ánh xạ đúng sử dụng một bảng trung gian (join table).

---

### **6. Using `@ElementCollection` for Collection of Embeddable Objects**

**Yêu cầu:**  
Tạo một entity `Order` với một collection các giá trị `OrderItem`. Entity `Order` nên có:

- `id` (Long) - Khóa chính, tự động sinh
- `orderNumber` (String)
- `orderItems` (Danh sách các đối tượng `OrderItem`) - Sử dụng `@ElementCollection` để ánh xạ các giá trị `OrderItem`.

Lớp `OrderItem` nên là một đối tượng embeddable với:

- `productName` (String)
- `quantity` (Integer)
- `price` (BigDecimal)

Đảm bảo collection được ánh xạ chính xác vào bảng `orders`.

---