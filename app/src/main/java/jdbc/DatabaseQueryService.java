package jdbc;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public static final String FIND_MAX_SALARY_WORKER = "./sql/find_max_salary_worker.sql";
    public static final String FIND_MAX_PROJECTS_CLIENT = "./sql/find_max_projects_client.sql";
    public static final String FIND_LONGEST_PROJECT = "./sql/find_longest_project.sql";
    public static final String FIND_YOUNGEST_ELDEST_WORKER = "./sql/find_youngest_eldest_worker.sql";
    public static final String PRINT_PROJECT_PRICES = "./sql/print_project_prices.sql";

    Database database = Database.getInstance();

    List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException, SQLException {

//        String sql = String.join("\n", Files.readString(Path.of(FIND_MAX_SALARY_WORKER)));
        String sql = Files.readString(Path.of(FIND_MAX_SALARY_WORKER));

        List<MaxSalaryWorker> listMaxSalaryWorker = new ArrayList<>();

        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                    maxSalaryWorker.setName(rs.getString("name"));
                    maxSalaryWorker.setSalary(rs.getInt("salary"));
                    listMaxSalaryWorker.add(maxSalaryWorker);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listMaxSalaryWorker;
    }

    List<MaxProjectCountClient> findMaxProjectsClient() throws IOException, SQLException {
//        String sql = String.join("\n", Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT)));
        String sql = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT));

        List<MaxProjectCountClient> listMaxProjectsClient = new ArrayList<>();

        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    MaxProjectCountClient maxProjectsClient = new MaxProjectCountClient();
                    maxProjectsClient.setName(rs.getString("name"));
                    maxProjectsClient.setProjectCount(rs.getInt("project_count"));
                    listMaxProjectsClient.add(maxProjectsClient);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listMaxProjectsClient;
    }

    List<LongestProject> findLongestProject() throws SQLException, IOException {
//        String sql = String.join("\n", Files.readString(Path.of(FIND_LONGEST_PROJECT)));
        String sql = Files.readString(Path.of(FIND_LONGEST_PROJECT));

        List<LongestProject> listLongestProject = new ArrayList<>();

        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    LongestProject longestProject = new LongestProject();
                    longestProject.setName(rs.getString("name"));
                    longestProject.setMonthCount(rs.getInt("month_count"));
                    listLongestProject.add(longestProject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listLongestProject;
    }

    List<YoungestEldestWorker> findYoungestEldestWorker() throws SQLException, IOException {
//        String sql = String.join("\n", Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKER)));
        String sql = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKER));

        List<YoungestEldestWorker> listYoungestEldestWorker = new ArrayList<>();

        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker();
                    youngestEldestWorker.setType(rs.getString("type"));
                    youngestEldestWorker.setName(rs.getString("name"));
                    youngestEldestWorker.setBirthday(rs.getString("birthday"));
                    listYoungestEldestWorker.add(youngestEldestWorker);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listYoungestEldestWorker;
    }

    List<ProjectPrices> printProjectPrices() throws SQLException, IOException {
//        String sql = String.join("\n", Files.readString(Path.of(PRINT_PROJECT_PRICES)));
        String sql = Files.readString(Path.of(PRINT_PROJECT_PRICES));

        List<ProjectPrices> listProjectPrices = new ArrayList<>();

        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    ProjectPrices projectPrices = new ProjectPrices();
                    projectPrices.setName(rs.getString("name"));
                    projectPrices.setPrice(rs.getInt("price"));
                    listProjectPrices.add(projectPrices);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listProjectPrices;
    }

    @Data
    public class MaxSalaryWorker {
        private String name;
        private int salary;
    }

    @Data
    public class MaxProjectCountClient {
        private String name;
        private int projectCount;
    }

    @Data
    public class LongestProject {
        private String name;
        private int monthCount;
    }

    @Data
    public class YoungestEldestWorker {
        private String type;
        private String name;
        private String birthday;
    }

    @Data
    public class ProjectPrices {
        private String name;
        private int price;
    }

    public static void main(String[] args) throws SQLException, IOException {
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();
        List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject();
        List<YoungestEldestWorker> youngestEldestWorker = new DatabaseQueryService().findYoungestEldestWorker();
        List<ProjectPrices> projectPrices = new DatabaseQueryService().printProjectPrices();


        maxSalaryWorker.forEach(System.out::println);
        maxProjectCountClients.forEach(System.out::println);
        longestProject.forEach(System.out::println);
        youngestEldestWorker.forEach(System.out::println);
        projectPrices.forEach(System.out::println);

    }
}
