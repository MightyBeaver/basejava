package ru.javawebinar.basejava.model;

import org.junit.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTest {
    @Test
    public void resumeCreationTest() throws Exception {
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
        Map<TimePeriod,String> javaOpsPeriodMap = new HashMap<>();
        javaOpsPeriodMap.put(new TimePeriod(LocalDate.of(2013, Month.OCTOBER,1), null),
                "Автор проекта.\n" +
                        "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experienceList.add(new Organization("Java Online Projects", "http://javaops.ru/",
                javaOpsPeriodMap));

        Map<TimePeriod,String> ritCenterPeriodMap = new HashMap<>();
        ritCenterPeriodMap.put(new TimePeriod(LocalDate.of(2012, Month.APRIL,1),
                LocalDate.of(2014, Month.OCTOBER, 1)),
                "Java архитектор\n" +
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование," +
                        " ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx)," +
                        " AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2," +
                        " 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN" +
                        " для online редактирование из браузера документов MS Office. Maven + plugin development, Ant," +
                        " Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
                        "Unix shell remote scripting via ssh tunnels, PL/Python");
        experienceList.add(new Organization("RIT Center", null, ritCenterPeriodMap));
        OrganizationsSection experience = new OrganizationsSection(experienceList);

        List<Organization> educationList = new ArrayList<>();
        Map<TimePeriod,String> itmoPeriodMap = new HashMap<>();
        itmoPeriodMap.put(new TimePeriod(LocalDate.of(1993,Month.SEPTEMBER,1),
                LocalDate.of(1996,Month.JULY,1)),
                "Аспирантура (программист С, С++)");
        itmoPeriodMap.put(new TimePeriod(LocalDate.of(1987,Month.SEPTEMBER,1),
                LocalDate.of(1993,Month.JULY,1)),
                "Инженер (программист Fortran, C)");
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет " +
                "информационных технологий, механики и оптики", "http://www.ifmo.ru/", itmoPeriodMap));
        OrganizationsSection education = new OrganizationsSection(educationList);

        Resume resume = new Resume("1", "Григорий Кислин");
        resume.addContact(Contacts.MOBILE_PHONE,"+7(921) 855-0482",null);
        resume.addContact(Contacts.GITHUB,"gkislin","https://github.com/gkislin");
        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.ACHIEVEMENTS, achievments);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
        resume.addSection(SectionType.EDUCATION, education);
    }
}