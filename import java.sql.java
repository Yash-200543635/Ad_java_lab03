import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtility {
    private static final String DB_URL = "jdbc:mysql:3306/AMA";
    private static final String DB_USER = "student";
    private static final String DB_PASSWORD = "student";

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return connection;
    }

    // Method to retrieve a list of students
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                students.add(new Student(studentId, firstName, lastName, email));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }

    // Method to retrieve a list of courses
    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String instructor = resultSet.getString("instructor");
                courses.add(new Course(courseId, courseName, instructor));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return courses;
    }

    // Method to retrieve attendance records
    public static List<AttendanceRecord> getAttendanceRecords() {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT * FROM attendance";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                int attendanceId = resultSet.getInt("attendance_id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");
                records.add(new AttendanceRecord(attendanceId, studentId, courseId, date, status));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }

    public static void main(String[] args) {
        List<Student> students = getAllStudents();
        List<Course> courses = getAllCourses();
        List<AttendanceRecord> attendanceRecords = getAttendanceRecords();
        
    }
}
