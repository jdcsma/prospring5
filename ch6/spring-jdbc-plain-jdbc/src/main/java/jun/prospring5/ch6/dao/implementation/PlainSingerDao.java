package jun.prospring5.ch6.dao.implementation;

import jun.prospring5.ch6.dao.SingerDao;
import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDao {

    private static Logger logger =
            LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Loading DB driver success.");
        } catch (ClassNotFoundException e) {
            logger.error("Problem loading DB driver:", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/music?" +
                        "useSSL=true&serverTimezone=UTC&characterEncoding=utf-8",
                "root", "root");
    }

    @Override
    public List<Singer> findAll() {

        List<Singer> result = new ArrayList<>();

        try (Connection connection = this.getConnection()) {

            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM `singer`");

            this.fetchSingers(statement.executeQuery(), result);
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return result;
    }

    @Override
    public List<Album> findAlbums(Long singerId) {

        List<Album> albums = new ArrayList<>();

        try (Connection connection = this.getConnection()) {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM `album` WHERE singer_id=?");
            statement.setLong(1, singerId);

            this.fetchAlbums(statement.executeQuery(), albums);
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return albums;
    }

    @Override
    public Singer findById(Long id) {

        Singer singer = null;

        try (Connection connection = this.getConnection()) {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT * FROM `singer` WHERE id=?");
            statement.setLong(1, id);

            singer = this.fetchSinger(statement.executeQuery());
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return singer;
    }

    @Override
    public String findFirstNameById(Long id) {

        String firstName = null;

        try (Connection connection = this.getConnection()) {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT `first_name` FROM `singer` WHERE `id`=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                firstName = resultSet.getString(1);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return firstName;
    }

    @Override
    public String findLastNameById(Long id) {

        String lastName = null;

        try (Connection connection = this.getConnection()) {

            PreparedStatement statement =
                    connection.prepareStatement(
                            "SELECT `last_name` FROM `singer` WHERE `id`=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                lastName = resultSet.getString(1);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return lastName;
    }

    @Override
    public void insert(Singer singer) {
        try (Connection connection = this.getConnection()) {
            this.insertSinger(connection, singer);
            for (Album album : singer.getAlbums()) {
                this.insertAlbum(connection, singer, album);
            }
        } catch (SQLException e) {
            logger.error("Problem when executing INSERT!", e);
        }
    }

    @Override
    public void update(Singer singer) {
        try (Connection connection = this.getConnection()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE `singer` " +
                                    "SET `first_name`=?,`last_name`=?,`birth_date`=?,`is_death`=? " +
                                    "WHERE `id`=?");
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.setBoolean(4, singer.isDeath());
            statement.setLong(5, singer.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when executing UPDATE!", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = this.getConnection()) {

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "DELETE FROM `album` WHERE `singer_id`=?")) {
                statement.setLong(1, id);
                statement.execute();
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "DELETE FROM `singer` WHERE `id`=?")) {
                statement.setLong(1, id);
                statement.execute();
            }

        } catch (SQLException e) {
            logger.error("Problem when executing DELETE!", e);
        }
    }

    @Override
    public List<Singer> findAllWithDetail() {

        List<Singer> singers = this.findAll();

        if (singers.size() == 0) {
            return singers;
        }

        try (Connection connection = this.getConnection()) {

            for (Singer singer : singers) {

                PreparedStatement statement =
                        connection.prepareStatement(
                                "SELECT * FROM `album` WHERE `singer_id`=?;");
                statement.setLong(1, singer.getId());

                this.fetchAlbums(statement.executeQuery(),
                        singer.getAlbums());
                statement.close();
            }

        } catch (SQLException e) {
            logger.error("Problem when executing SELECT!", e);
        }

        return singers;
    }

    private void insertSinger(
            Connection connection, Singer singer)
            throws SQLException {

        PreparedStatement statement =
                connection.prepareStatement(
                        "INSERT INTO `singer` (`first_name`," +
                                "`last_name`,`birth_date`,`is_death`) " +
                                "VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, singer.getFirstName());
        statement.setString(2, singer.getLastName());
        statement.setDate(3, singer.getBirthDate());
        statement.setBoolean(4, singer.isDeath());

        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            singer.setId(resultSet.getLong(1));
        }
        statement.close();
    }

    private void insertAlbum(
            Connection connection, Singer singer, Album album)
            throws SQLException {
        album.setSingerId(singer.getId());
        PreparedStatement statement =
                connection.prepareStatement(
                        "INSERT INTO `album` (`singer_id`, " +
                                "`title`, `release_date`) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, album.getSingerId());
        statement.setString(2, album.getTitle());
        statement.setDate(3, album.getReleaseDate());

        statement.execute();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            album.setId(resultSet.getLong(1));
        }
        statement.close();
    }

    private void fetchSingers(
            ResultSet resultSet, List<Singer> singers)
            throws SQLException {

        Singer singer;

        while ((singer = this.fetchSinger(resultSet)) != null) {
            singers.add(singer);
        }
    }

    private Singer fetchSinger(ResultSet resultSet) throws SQLException {

        Singer singer = null;

        if (resultSet.next()) {
            singer = new Singer();
            singer.setId(resultSet.getLong("id"));
            singer.setFirstName(resultSet.getString("first_name"));
            singer.setLastName(resultSet.getString("last_name"));
            singer.setBirthDate(resultSet.getDate("birth_date"));
            singer.setDeath(resultSet.getBoolean("is_death"));
        }

        return singer;
    }

    private void fetchAlbums(
            ResultSet resultSet, List<Album> albums)
            throws SQLException {

        Album album;

        while ((album = this.fetchAlbum(resultSet)) != null) {
            albums.add(album);
        }
    }

    private Album fetchAlbum(ResultSet resultSet) throws SQLException {

        Album album = null;

        if (resultSet.next()) {
            album = new Album();
            album.setId(resultSet.getLong("id"));
            album.setSingerId(resultSet.getLong("singer_id"));
            album.setTitle(resultSet.getString("title"));
            album.setReleaseDate(resultSet.getDate("release_date"));
        }

        return album;
    }
}
