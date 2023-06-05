import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() {
        // MySQL 연결 정보 (jdbc:mysql -> sql 쿼리를 전달하는 프로토콜)
        String url = "jdbc:mysql://localhost:3306/metadb";
        String username = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("디버그 : DB연결 성공");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
