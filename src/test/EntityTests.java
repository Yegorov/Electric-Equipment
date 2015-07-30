import electric.model.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class EntityTests {

    @Test
    public void testRecordData() {
        Record r = new Record(100, "Text");
        assertEquals(r.getId(), 100);
        assertEquals(r.getTitle(), "Text");
    }

    @Test
    public void testVoltageRelayData() {
        VoltageRelay vr = new VoltageRelay(19,
                "Rele #67 zczxcsd v s",
                1,
                "Siemens",
                5,
                "�� DIN �����",
                3,
                4,
                4,
                24.2,
                12d,
                15.2,
                220.2,
                400.5,
                55000d,
                "�������� ����.....");

        assertEquals(vr.getId(), 19);
        assertEquals(vr.getTitle(), "Rele #67 zczxcsd v s");
        assertEquals(vr.getIdManufacturer(), 1);
        assertEquals(vr.getManufacturerTitle(), "Siemens");

        assertEquals(vr.getMounting(), "�� DIN �����");
        assertEquals(vr.getCountPhase(), 3);
        assertEquals(vr.getNumberNcContacts(), 4);
        assertEquals(vr.getNumberNoContacts(), 4);
        assertEquals(vr.getRatedCoilVoltage(), 24.2);
        assertEquals(vr.getRatedCurrent(), 12d);
        assertEquals(vr.getMaxCurrent(), 15.2);
        assertEquals(vr.getRatedVoltage(), 220.2);
        assertEquals(vr.getMaxVoltage(), 400.5);
        assertEquals(vr.getRatedPower(), 55000d);
        assertEquals(vr.getDescription(), "�������� ����.....");

    }

    @Test
    public void testTransformerData() {
        Transformer t = new Transformer(5,
                "transformator T-42N",
                1,
                "ABB",
                2,
                "�������������� ����������",
                3,
                "����������",
                3,
                "����������",
                4,
                "�����",
                2,
                "������",
                2,
                "����",
                3,
                5,
                4.3,
                23.6,
                40.7,
                "�������� ��������������");

        assertEquals(t.getId(), 5);
        assertEquals(t.getTitle(), "transformator T-42N");
        assertEquals(t.getIdManufacturer(), 1);
        assertEquals(t.getManufacturerTitle(), "ABB");

        assertEquals(t.getCategory(), "�������������� ����������");
        assertEquals(t.getType(), "����������");
        assertEquals(t.getCore(), "����������");
        assertEquals(t.getCooling(), "�����");
        assertEquals(t.getWindingConfiguration(), "������");
        assertEquals(t.getMaterialWinding(), "����");

        assertEquals(t.getCountWinding(), 3);
        assertEquals(t.getCountPhase(), 5);
        assertEquals(t.getRatedCurrent(), 4.3);
        assertEquals(t.getPrimaryWindingVoltage(), 23.6);
        assertEquals(t.getSecondaryWindingVoltage(), 40.7);

        assertEquals(t.getDescription(), "�������� ��������������");

    }


    @Test
    public void testCountryData() {
        Country c = new Country(12, "�������", "UA");

        assertEquals(c.getId(), 12);
        assertEquals(c.getCountry(), "�������");
        assertEquals(c.getCountryCode(), "UA");

        c.setId(21);
        c.setCountry("������");
        c.setCountryCode("RU");

        assertEquals(c.getId(), 21);
        assertEquals(c.getCountry(), "������");
        assertEquals(c.getCountryCode(), "RU");
    }

    @Test
    public void testData() {
        Manufacturer m = new Manufacturer(1,
                "����������",
                1,
                "�������",
                "UA",
                "www.energo.com",
                "mymail@yandex.ru,secondmail@ru.ru",
                "+38(050)99-23-232,+38(095)99-23-234",
                "��������");

        assertEquals(m.getId(), 1);
        assertEquals(m.getTitle(), "����������");
        assertEquals(m.getCountry(), "�������");
        assertEquals(m.getCountryCode(), "UA");
        assertEquals(m.getCountryWithCode(), "������� (UA)");
        assertEquals(m.getSite(), "www.energo.com");
        assertEquals(m.getDescription(), "��������");

        assertEquals(m.getCountPhoneNumbers(), 2);
        assertEquals(m.getPhoneNumber(0), "+38(050)99-23-232");
        assertEquals(m.getPhoneNumber(1), "+38(095)99-23-234");

        assertEquals(m.getCountEmails(), 2);
        assertEquals(m.getEmail(0), "mymail@yandex.ru");
        assertEquals(m.getEmail(1), "secondmail@ru.ru");

        assertEquals(m.getStringEmails(), "mymail@yandex.ru,secondmail@ru.ru");
        assertEquals(m.getStringPhoneNumbers(), "+38(050)99-23-232,+38(095)99-23-234");

        //assertEquals(m.getCountPhoneNumbers(), 2);
    }
}
