package electric.model.sql;

import electric.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String filename;
    private boolean existsDb;

    public DatabaseHandler() {
        filename = "electric.db";
        existsDb = false;

    }


    public void insert(Transformer t) {
        PreparedStatement statement = null;
        String insertRecord = "INSERT INTO `transformers`(`title`, " +
                "`id_manufacturer`, `id_category`, `id_type`, `id_core`, " +
                "`id_cooling`, `id_winding_configuration`, `id_material_winding`, " +
                "`count_winding`, `count_phase`, `rated_current`, `primary_winding_voltage`, " +
                "`secondary_winding_voltage`, `description`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, t.getTitle());
            statement.setInt(2, t.getIdManufacturer());
            statement.setInt(3, t.getIdCategory());
            statement.setInt(4, t.getIdType());
            statement.setInt(5, t.getIdCore());
            statement.setInt(6, t.getIdCooling());
            statement.setInt(7, t.getIdWindingConfiguration());
            statement.setInt(8, t.getIdMaterialWinding());
            statement.setInt(9, t.getCountWinding());
            statement.setInt(10, t.getCountPhase());
            statement.setDouble(11, t.getRatedCurrent());
            statement.setDouble(12, t.getPrimaryWindingVoltage());
            statement.setDouble(13, t.getSecondaryWindingVoltage());
            statement.setString(14, t.getDescription());


            t.setId(insert(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Transformer t) {
        PreparedStatement statement = null;
        String updateRecord = "UPDATE `transformers` SET `title` = ?, " +
                "`id_manufacturer` = ?, `id_category` = ?, `id_type` = ?, `id_core` = ?, " +
                "`id_cooling` = ?, `id_winding_configuration` = ?, `id_material_winding` = ?, " +
                "`count_winding` = ?, `count_phase` = ?, `rated_current` = ?, `primary_winding_voltage` = ?, " +
                "`secondary_winding_voltage` = ?, `description` = ? " +
                "WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(updateRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, t.getTitle());
            statement.setInt(2, t.getIdManufacturer());
            statement.setInt(3, t.getIdCategory());
            statement.setInt(4, t.getIdType());
            statement.setInt(5, t.getIdCore());
            statement.setInt(6, t.getIdCooling());
            statement.setInt(7, t.getIdWindingConfiguration());
            statement.setInt(8, t.getIdMaterialWinding());
            statement.setInt(9, t.getCountWinding());
            statement.setInt(10, t.getCountPhase());
            statement.setDouble(11, t.getRatedCurrent());
            statement.setDouble(12, t.getPrimaryWindingVoltage());
            statement.setDouble(13, t.getSecondaryWindingVoltage());
            statement.setString(14, t.getDescription());
            statement.setInt(15, t.getId());

            return update(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean update(PreparedStatement statement) {
        boolean d = false;
        try {
            int a = statement.executeUpdate();
            if(a > 0)
                d = true;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public boolean update(VoltageRelay vr) {
        PreparedStatement statement = null;
        String updateRecord = "UPDATE `voltage_relays` SET `title` = ?,  " +
                "`id_manufacturer` = ?, `id_mounting` = ?, `count_phase` = ?, `number_nc_contacts` = ?, " +
                "`number_no_contacts` = ?, `rated_coil_voltage` = ?, `rated_current` = ?, " +
                "`max_current` = ?, `rated_voltage` = ?, `max_voltage` = ?, `rated_power` = ?, `description` = ? " +
                "WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(updateRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, vr.getTitle());
            statement.setInt(2, vr.getIdManufacturer());
            statement.setInt(3, vr.getIdMounting());
            statement.setInt(4, vr.getCountPhase());
            statement.setInt(5, vr.getNumberNcContacts());
            statement.setInt(6, vr.getNumberNoContacts());
            statement.setDouble(7, vr.getRatedCoilVoltage());
            statement.setDouble(8, vr.getRatedCurrent());
            statement.setDouble(9, vr.getMaxCurrent());
            statement.setDouble(10, vr.getRatedVoltage());
            statement.setDouble(11, vr.getMaxVoltage());
            statement.setDouble(12, vr.getRatedPower());
            statement.setString(13, vr.getDescription());

            statement.setInt(14, vr.getId());

            return update(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(VoltageRelay vr) {
        PreparedStatement statement = null;
        String insertRecord = "INSERT INTO `voltage_relays`(`title`,  " +
                "`id_manufacturer`, `id_mounting`, `count_phase`, `number_nc_contacts`, " +
                "`number_no_contacts`, `rated_coil_voltage`, `rated_current`, " +
                "`max_current`, `rated_voltage`, `max_voltage`, `rated_power`, `description`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, vr.getTitle());
            statement.setInt(2, vr.getIdManufacturer());
            statement.setInt(3, vr.getIdMounting());
            statement.setInt(4, vr.getCountPhase());
            statement.setInt(5, vr.getNumberNcContacts());
            statement.setInt(6, vr.getNumberNoContacts());
            statement.setDouble(7, vr.getRatedCoilVoltage());
            statement.setDouble(8, vr.getRatedCurrent());
            statement.setDouble(9, vr.getMaxCurrent());
            statement.setDouble(10, vr.getRatedVoltage());
            statement.setDouble(11, vr.getMaxVoltage());
            statement.setDouble(12, vr.getRatedPower());
            statement.setString(13, vr.getDescription());

            vr.setId(insert(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Manufacturer m) {
        PreparedStatement statement = null;
        String insertRecord = "UPDATE `manufacturers` " +
                "SET `title` = ?, `id_country` = ?, `phone_numbers` = ?, `emails` = ?, `site` = ?, `description` = ? " +
                "WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, m.getTitle());
            statement.setInt(2, m.getIdCountry());
            statement.setString(3, m.getStringPhoneNumbers());
            statement.setString(4, m.getStringEmails());
            statement.setString(5, m.getSite());
            statement.setString(6, m.getDescription());

            statement.setInt(7, m.getId());

            return update(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Manufacturer m) {
        PreparedStatement statement = null;
        String insertRecord = "INSERT INTO `manufacturers` " +
                              "(`title`, `id_country`, `phone_numbers`, `emails`, `site`, `description`) " +
                              "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);

            statement.setString(1, m.getTitle());
            statement.setInt(2, m.getIdCountry());
            statement.setString(3, m.getStringPhoneNumbers());
            statement.setString(4, m.getStringEmails());
            statement.setString(5, m.getSite());
            statement.setString(6, m.getDescription());

            m.setId(insert(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Country country) {
        PreparedStatement statement = null;
        String updateRecord = "UPDATE `countries` SET `country` = ?, `country_code` = ?" +
                "WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(updateRecord);
            statement.setQueryTimeout(30);
            statement.setString(1, country.getCountry());
            statement.setString(2, country.getCountryCode());

            statement.setInt(3, country.getId());

            return update(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Country country) {
        PreparedStatement statement = null;
        String insertRecord = "INSERT INTO `countries`(`country`, `country_code`) " +
                "VALUES (?, ?);";
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);
            statement.setString(1, country.getCountry());
            statement.setString(2, country.getCountryCode());
            country.setId(insert(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Record record, String nameTable) {
        PreparedStatement statement = null;
        String insertRecord = String.format("UPDATE `%s` SET `title`= ? WHERE `id` = ?;", nameTable);
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);
            statement.setString(1, record.getTitle());
            statement.setInt(2, record.getId());

            return update(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(Record record, String nameTable) {
        PreparedStatement statement = null;
        String insertRecord = String.format("INSERT INTO `%s`(`title`) VALUES(?);", nameTable);
        try {
            statement = connection.prepareStatement(insertRecord);
            statement.setQueryTimeout(30);
            statement.setString(1, record.getTitle());
            record.setId(insert(statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(PreparedStatement statement) {
        int id = 0;
        try {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                id = 0;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean delete(Record r, String nameTable) {
        PreparedStatement statement = null;
        String deleteRecord = String.format("DELETE FROM `%s` WHERE `id` = ?;", nameTable);
        try {
            statement = connection.prepareStatement(deleteRecord);
            statement.setQueryTimeout(30);
            statement.setInt(1, r.getId());

            return delete(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(VoltageRelay vr) {
        PreparedStatement statement = null;
        String deleteRecord = "DELETE FROM `voltage_relays` WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(deleteRecord);
            statement.setQueryTimeout(30);
            statement.setInt(1, vr.getId());

            return delete(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Transformer t) {
        PreparedStatement statement = null;
        String deleteRecord = "DELETE FROM `transformers` WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(deleteRecord);
            statement.setQueryTimeout(30);
            statement.setInt(1, t.getId());

            return delete(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Manufacturer m) {
        PreparedStatement statement = null;
        String deleteRecord = "DELETE FROM `manufacturers` WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(deleteRecord);
            statement.setQueryTimeout(30);
            statement.setInt(1, m.getId());

            return delete(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Country c) {
        PreparedStatement statement = null;
        String deleteRecord = "DELETE FROM `countries` WHERE `id` = ?;";
        try {
            statement = connection.prepareStatement(deleteRecord);
            statement.setQueryTimeout(30);
            statement.setInt(1, c.getId());

            return delete(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean delete(PreparedStatement statement) {
        boolean d = false;
        try {
            int a = statement.executeUpdate();
            if(a > 0)
                d = true;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public List<Country> selectAllCountry() {
        String selectRecord = "SELECT `id`, `country`, `country_code` " +
                              "FROM `countries` " +
                              "ORDER BY `id`;";
        List<Country> list = new ArrayList<>(50);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectRecord);
            while (rs.next()) {
                list.add(new Country(rs.getInt("id"), rs.getString("country"), rs.getString("country_code")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Manufacturer> selectAllManufacturer() {
        String selectRecord = "SELECT manufacturers.`id`, manufacturers.`title`, manufacturers.`id_country`, " +
                "countries.`country`, countries.`country_code`, manufacturers.`phone_numbers`, manufacturers.`emails`, " +
                "manufacturers.`site`, manufacturers.`description`" +
                "FROM countries INNER JOIN manufacturers ON countries.id = manufacturers.id_country " +
                "ORDER BY manufacturers.`id`;";
        List<Manufacturer> list = new ArrayList<>(50);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectRecord);
            while (rs.next()) {
                list.add(new Manufacturer(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("id_country"),
                        rs.getString("country"),
                        rs.getString("country_code"),
                        rs.getString("site"),
                        rs.getString("emails"),
                        rs.getString("phone_numbers"),
                        rs.getString("description")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Transformer> selectAllTransformer() {
        String selectRecord = "SELECT " +
                "transformers.id, " +
                "transformers.title, " +
                "transformers.id_manufacturer, " +
                "manufacturers.title, " +
                "transformers.id_category, " +
                "categories_transformers.title, " +
                "transformers.id_type, " +
                "types_transformers.title, " +
                "transformers.id_core, " +
                "cores.title, " +
                "transformers.id_cooling, " +
                "cooling_types.title, " +
                "transformers.id_winding_configuration, " +
                "winding_configuration.title, " +
                "transformers.id_material_winding, " +
                "materials.title, " +
                "transformers.count_winding, " +
                "transformers.count_phase, " +
                "transformers.rated_current, " +
                "transformers.primary_winding_voltage, " +
                "transformers.secondary_winding_voltage, " +
                "transformers.description " +
                "FROM manufacturers INNER JOIN  " +
                "(categories_transformers INNER JOIN  " +
                "(types_transformers INNER JOIN  " +
                "(cores INNER JOIN  " +
                "(cooling_types INNER JOIN  " +
                "(winding_configuration INNER JOIN  " +
                "(materials INNER JOIN transformers ON materials.id = transformers.id_material_winding)  " +
                "ON winding_configuration.id = transformers.id_winding_configuration)  " +
                "ON cooling_types.id = transformers.id_cooling)  " +
                "ON cores.id = transformers.id_core)  " +
                "ON types_transformers.id = transformers.id_type)  " +
                "ON categories_transformers.id = transformers.id_category)  " +
                "ON manufacturers.id = transformers.id_manufacturer " +
                "ORDER BY transformers.id;";
        List<Transformer> list = new ArrayList<>(50);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectRecord);
            while (rs.next()) {
                list.add(new Transformer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getInt(11),
                        rs.getString(12),
                        rs.getInt(13),
                        rs.getString(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17),
                        rs.getInt(18),
                        rs.getDouble(19),
                        rs.getDouble(20),
                        rs.getDouble(21),
                        rs.getString(22)
                ));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<VoltageRelay> selectAllVoltageRelay() {
        String selectRecord = "SELECT " +
                "voltage_relays.id, " +
                "voltage_relays.title, " +
                "voltage_relays.id_manufacturer, " +
                "manufacturers.title, " +
                "voltage_relays.id_mounting, " +
                "types_mounting.title, " +
                "voltage_relays.count_phase, " +
                "voltage_relays.number_nc_contacts, " +
                "voltage_relays.number_no_contacts, " +
                "voltage_relays.rated_coil_voltage, " +
                "voltage_relays.rated_current, " +
                "voltage_relays.max_current, " +
                "voltage_relays.rated_voltage, " +
                "voltage_relays.max_voltage, " +
                "voltage_relays.rated_power, " +
                "voltage_relays.description " +
                "FROM manufacturers INNER JOIN  " +
                "(types_mounting INNER JOIN voltage_relays ON types_mounting.id = voltage_relays.id_mounting) " +
                " ON manufacturers.id = voltage_relays.id_manufacturer " +
                "ORDER BY 1;";
        List<VoltageRelay> list = new ArrayList<>(50);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectRecord);
            while (rs.next()) {
                list.add(new VoltageRelay(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getDouble(10),
                        rs.getDouble(11),
                        rs.getDouble(12),
                        rs.getDouble(13),
                        rs.getDouble(14),
                        rs.getDouble(15),
                        rs.getString(16)
                ));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Record> selectAllRecord(String nameTable) {
        String selectRecord = "SELECT `id`, `title` " +
                              String.format("FROM `%s`", nameTable) +
                              "ORDER BY `id`;";
        List<Record> list = new ArrayList<>(50);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectRecord);
            while (rs.next()) {
                list.add(new Record(rs.getInt("id"), rs.getString("title")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isConnect() {
        boolean c = false;
        if(connection == null)
            return false;
        try {
            c= !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void connect() {
        File f = new File(filename);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON");
            statement.close();

            if(!existsDb) {
                createTable();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String getTableSql = "SELECT COUNT(*) FROM sqlite_master WHERE type='table';";
            if(statement.executeQuery(getTableSql).getInt(1) == 11) {
                existsDb = true;
            }
            if(!existsDb) {
                String[] createSqls = getText(DatabaseHandler.class, "init.sql");

                for(String s : createSqls) {
                    statement.executeUpdate(s);
                }

                createSqls = getText(DatabaseHandler.class, "extended.sql");
                for(String s : createSqls) {
                    statement.executeUpdate(s);
                }

                createSqls = getText(DatabaseHandler.class, "extended2.sql");
                for(String s : createSqls) {
                    statement.executeUpdate(s);
                }
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String[] getText(Class c, String name) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        c.getResourceAsStream(name)));
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            while((s = reader.readLine()) != null)
                sb.append(s + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().split(";");
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
