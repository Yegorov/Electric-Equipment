import electric.model.*;
import electric.model.sql.DatabaseHandler;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DatabaseTests {
    @Test
    public void databaseCreate() {
        DatabaseHandler db = new DatabaseHandler();
        assertEquals(db.isConnect(), false);
        db.connect();
        assertEquals(db.isConnect(), true);


        //insertCountry(db);
        //insertRecord(db);
        //insertManufacturer(db);
        //insertVoltageRelay(db);
        //insertTransformer(db);

        //deleteCountry(db);
        //deleteRecord(db);
        //deleteManufacturer(db);
        //deleteVoltageRelay(db);
        //deleteTransformer(db);

        //updateCountry(db);
        //updateRecord(db);
        //updateManufacturer(db);
        //updateVoltageRelay(db);
        //updateTransformer(db);

        //db.selectAllCountry().stream().forEach(c -> System.out.println(c.toString()));
        //db.selectAllManufacturer().stream().forEach(m -> System.out.println(m.toString()));
        db.selectAllTransformer().stream().forEach(a -> System.out.println(a.toString()));
        db.selectAllVoltageRelay().stream().forEach(a -> System.out.println(a.toString()));
        db.selectAllRecord("categories_transformers").stream().forEach(a -> System.out.println(a.toString()));
        db.selectAllRecord("types_transformers").stream().forEach(a -> System.out.println(a.toString()));
        db.selectAllRecord("cores").stream().forEach(a -> System.out.println(a.toString()));
        db.selectAllRecord("types_mounting").stream().forEach(a -> System.out.println(a.toString()));

        db.close();
        assertEquals(db.isConnect(), false);

    }

    @Ignore
    public void deleteCountry(DatabaseHandler db) {
        Country c = new Country(24, "Австралия", "AA");
        assertEquals(db.delete(c), true);
    }


    @Ignore
     public void insertCountry(DatabaseHandler db ) {
        Country c = new Country(0, "Австралия", "AA");
        db.insert(c);
        assertEquals(c.getId(), 24);
    }

    @Ignore
    public void deleteRecord(DatabaseHandler db) {
        Record r = new Record(4, "Золото");
        assertEquals(db.delete(r, "materials"), true);
    }


    @Ignore
    public void insertRecord(DatabaseHandler db ) {
        Record r = new Record(0, "Золото");
        db.insert(r, "materials");
        assertEquals(r.getId(), 4);
    }

    @Ignore
    public void deleteManufacturer(DatabaseHandler db) {
        Manufacturer m = new Manufacturer(41,
                "ЭнергоПром",
                1,
                "Украина",
                "UA",
                "www.energo.com",
                "mymail@yandex.ru,secondmail@ru.ru",
                "+38(050)99-23-232,+38(095)99-23-234",
                "Описание");
        assertEquals(db.delete(m), true);
    }

    @Ignore
    public void insertManufacturer(DatabaseHandler db) {
        Manufacturer m = new Manufacturer(0,
                "ЭнергоПром",
                1,
                "Украина",
                "UA",
                "www.energo.com",
                "mymail@yandex.ru,secondmail@ru.ru",
                "+38(050)99-23-232,+38(095)99-23-234",
                "Описание");
        db.insert(m);
        assertEquals(m.getId(), 41);

    }


    @Ignore
    public void deleteVoltageRelay(DatabaseHandler db) {
        VoltageRelay vr = new VoltageRelay(3,
                "Rele #67 zczxcsd v s",
                1,
                "Siemens",
                5,
                "На DIN рейку",
                3,
                4,
                4,
                24.2,
                12d,
                15.2,
                220.2,
                400.5,
                55000d,
                "Описание реле.....");
        assertEquals(db.delete(vr), true);

    }

    @Ignore
    public void insertVoltageRelay(DatabaseHandler db) {
        VoltageRelay vr = new VoltageRelay(0,
                "Rele #67 zczxcsd v s",
                1,
                "Siemens",
                5,
                "На DIN рейку",
                3,
                4,
                4,
                24.2,
                12d,
                15.2,
                220.2,
                400.5,
                55000d,
                "Описание реле.....");
        db.insert(vr);
        assertEquals(vr.getId(), 3);

    }

    @Ignore
    public void deleteTransformer(DatabaseHandler db) {
        Transformer t = new Transformer(3,
                "transformator T-42N",
                1,
                "ABB",
                2,
                "Трансформаторы напряжения",
                3,
                "Понижающий",
                3,
                "Стержневое",
                4,
                "Сухой",
                2,
                "Звезда",
                2,
                "Медь",
                3,
                5,
                4.3,
                23.6,
                40.7,
                "Описание трансформатора");
        assertEquals(db.delete(t), true);
    }

    @Ignore
    public void insertTransformer(DatabaseHandler db) {
        Transformer t = new Transformer(0,
                "transformator T-42N",
                1,
                "ABB",
                2,
                "Трансформаторы напряжения",
                3,
                "Понижающий",
                3,
                "Стержневое",
                4,
                "Сухой",
                2,
                "Звезда",
                2,
                "Медь",
                3,
                5,
                4.3,
                23.6,
                40.7,
                "Описание трансформатора");
        db.insert(t);
        assertEquals(t.getId(), 3);

    }

    @Ignore
    public void updateCountry(DatabaseHandler db) {
        Country c = new Country(24, "Австралия (new record)", "ZZ");
        assertEquals(db.update(c), true);
    }

    @Ignore
    public void updateRecord(DatabaseHandler db ) {
        Record r = new Record(4, "Золото");
        assertEquals(db.update(r, "materials"), true);
    }

    @Ignore
    public void updateManufacturer(DatabaseHandler db) {
        Manufacturer m = new Manufacturer(41,
                "ЭнергоПром (new)",
                1,
                "Украина",
                "UA",
                "www.newenergo.com",
                "newmymail@yandex.ru,newsecondmail@ru.ru",
                "+38(050)99-23-232,+38(095)99-23-234",
                "New Описание");
        assertEquals(db.update(m), true);
    }

    @Ignore
    public void updateVoltageRelay(DatabaseHandler db) {
        VoltageRelay vr = new VoltageRelay(3,
                "new Rele #67",
                1,
                "Siemens",
                5,
                "На DIN рейку",
                31,
                41,
                41,
                241.2,
                121d,
                151.2,
                2201.2,
                4001.5,
                550001d,
                "New Описание реле");
        assertEquals(db.update(vr), true);
    }

    @Ignore
    public void updateTransformer(DatabaseHandler db) {
        Transformer t = new Transformer(3,
                "transformator T-43 Новый 123",
                1,
                "ABB",
                2,
                "Трансформаторы напряжения",
                3,
                "Понижающий",
                3,
                "Стержневое",
                4,
                "Сухой",
                2,
                "Звезда",
                2,
                "Медь",
                100,
                100,
                100.3,
                100.6,
                100.7,
                "Новое описание трансформатора");
        db.update(t);
        assertEquals(t.getId(), 3);
        assertEquals(t.getTitle(), "transformator T-43 Новый 123");
        assertEquals(t.getDescription(), "Новое описание трансформатора");

    }
}
