//package hyunsb.hellospring.repository;
//
//import hyunsb.hellospring.domain.Member;
//import org.springframework.jdbc.datasource.DataSourceUtils;
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class JdbcMemberRepository implements MemberRepository {
//    private final DataSource dataSource;
//
//    public JdbcMemberRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public Member save(Member member) {
//        String sql = "insert into member(name) values(?)";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql,
//                    Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, member.getName());
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                member.setId(rs.getLong(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
//            return member;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByName(String name) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return null;
//    }
//
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
//        try {
//            if (rs != null) {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (conn != null) {
//                    close(conn);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//        DataSourceUtils.releaseConnection(conn, dataSource);}
//
//}