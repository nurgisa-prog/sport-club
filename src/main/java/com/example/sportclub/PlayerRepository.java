package com.example.sportclub;



import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/oopdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM player")) {

            while (rs.next()) {
                players.add(
                        new Player(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }
    public boolean update(int id, Player player) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps =
                     con.prepareStatement(
                             "UPDATE player SET name = ?, age = ? WHERE id = ?"
                     )) {

            ps.setString(1, player.getName());
            ps.setInt(2, player.getAge());
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Failed to update player");
        }
    }
    public boolean delete(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps =
                     con.prepareStatement(
                             "DELETE FROM player WHERE id = ?"
                     )) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete player");
        }
    }

    public void save(Player player) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps =
                     con.prepareStatement(
                             "INSERT INTO player(name, age) VALUES (?, ?)"
                     )) {

            ps.setString(1, player.getName());
            ps.setInt(2, player.getAge());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
