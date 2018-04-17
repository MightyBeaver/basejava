package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    protected static final Resume[] testResumes = new Resume[]
            {
                    new Resume(UUID_1, "John Smith"),
                    new Resume(UUID_2, "Григорий Кислин"),
                    new Resume(UUID_3, "Bill Evans"),
                    new Resume(UUID_4, "Edsger Dijkstra")
            };

   public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeClass
    public static void resumeSetUp() {
        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web " +
                "и Enterprise технологиям");

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры.");

        List<String> achievmentList = new ArrayList<>();
        achievmentList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        achievmentList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ListSection achievments = new ListSection(achievmentList);

        List<String> qualificationsList = new ArrayList<>();
        qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        ListSection qualifications = new ListSection(qualificationsList);

        List<Organization> experienceList = new ArrayList<>();
        List<Organization.Position> javaOpsPeriodList = new ArrayList<>();
        javaOpsPeriodList.add(new Organization.Position(LocalDate.of(2013, Month.OCTOBER,1), DateUtil.NOW,
                "Автор проекта.\n" +
                        "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experienceList.add(new Organization(new Link("Java online projects" , "http://javaops.ru/"), javaOpsPeriodList));

        List<Organization.Position> ritPositionList = new ArrayList<>();
        ritPositionList.add(new Organization.Position(LocalDate.of(2012, Month.APRIL,1),
                        LocalDate.of(2014, Month.OCTOBER, 1),
                "Java архитектор\n" +
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование," +
                        " ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx)," +
                        " AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2," +
                        " 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN" +
                        " для online редактирование из браузера документов MS Office. Maven + plugin development, Ant," +
                        " Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                        "Unix shell remote scripting via ssh tunnels, PL/Python"));
        experienceList.add(new Organization(new Link("RIT Center", null), ritPositionList));
        OrganizationsSection experience = new OrganizationsSection(experienceList);

        List<Organization> educationList = new ArrayList<>();
        List<Organization.Position> itmoPositionList = new ArrayList<>();
        itmoPositionList.add(new Organization.Position(LocalDate.of(1993,Month.SEPTEMBER,1),
                        LocalDate.of(1996,Month.JULY,1),
                "Аспирантура (программист С, С++)"));
        itmoPositionList.add(new Organization.Position(LocalDate.of(1987,Month.SEPTEMBER,1),
                        LocalDate.of(1993,Month.JULY,1),
                "Инженер (программист Fortran, C)"));
        educationList.add(new Organization(new Link("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "http://www.ifmo.ru/"), itmoPositionList));
        OrganizationsSection education = new OrganizationsSection(educationList);
        /*testResumes[1].addContact(Contacts.MOBILE_PHONE,"+7(921) 855-0482",null);
        testResumes[1].addContact(Contacts.GITHUB,"gkislin","https://github.com/gkislin");
        testResumes[1].addSection(SectionType.OBJECTIVE, objective);
        testResumes[1].addSection(SectionType.PERSONAL, personal);
        testResumes[1].addSection(SectionType.ACHIEVEMENTS, achievments);
        testResumes[1].addSection(SectionType.EXPERIENCE, experience);
        testResumes[1].addSection(SectionType.QUALIFICATIONS, qualifications);
        testResumes[1].addSection(SectionType.EDUCATION, education);*/
    }
    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(testResumes[0]);
        storage.save(testResumes[1]);
        storage.save(testResumes[2]);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        storage.update(testResumes[1]);
        Assert.assertTrue(testResumes[1].equals(storage.get(testResumes[1].getUuid())));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNonExistent() throws Exception {
        storage.update(testResumes[3]);
    }

    @Test
    public void save() throws Exception {
        storage.save(testResumes[3]);
        Assert.assertEquals(4,storage.size());
        Assert.assertTrue(testResumes[3].equals(storage.get(testResumes[3].getUuid())));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistent() throws Exception {
        storage.save(testResumes[0]);
    }


    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(testResumes[0].getUuid());
        Assert.assertEquals(2,storage.size());
        storage.get(testResumes[0].getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNonExistent() throws Exception {
        storage.delete("asdf");
    }

    @Test
    public void get() throws Exception {
        Assert.assertTrue(testResumes[1].equals(storage.get(testResumes[1].getUuid())));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistent() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> fromStorage = storage.getAllSorted();
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(testResumes[0],fromStorage.get(1));
        Assert.assertEquals(testResumes[1],fromStorage.get(2));
        Assert.assertEquals(testResumes[2],fromStorage.get(0));
    }


}