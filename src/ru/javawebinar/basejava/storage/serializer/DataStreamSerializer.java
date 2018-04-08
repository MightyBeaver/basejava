package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.ResumeSerializer;

import javax.xml.soap.Text;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements ResumeSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<Contacts, Link> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet() ,
                    (entry) -> {
                       dos.writeUTF(entry.getKey().name());
                       dos.writeUTF(entry.getValue().getName());
                       dos.writeUTF(entry.getValue().getUrl());
                    });

            Map<SectionType, Section> sections = r.getSections();
            writeCollection(dos, sections.entrySet(),
                    (entry) -> {
                        SectionType sectionType = entry.getKey();
                        Section section = entry.getValue();
                        dos.writeUTF(sectionType.name());
                        switch (sectionType) {
                            case PERSONAL:
                            case OBJECTIVE:
                                dos.writeUTF(((TextSection)section).getDescription()); break;
                            case ACHIEVEMENTS:
                            case QUALIFICATIONS:
                                writeCollection(dos, ((ListSection) section).getParagraphs(), dos::writeUTF); break;
                            case EXPERIENCE:
                            case EDUCATION:
                                writeCollection(dos, ((OrganizationsSection) section).getOrganizations(),
                                        (org) -> {
                                            Link homePage = org.getHomePage();
                                            dos.writeUTF(homePage.getName());
                                            dos.writeUTF(homePage.getUrl());
                                            writeCollection(dos, org.getPositions(),
                                                    (pos) -> {
                                                        writeLocalDate(dos, pos.getStartDate());
                                                        writeLocalDate(dos, pos.getEndDate());
                                                        dos.writeUTF(pos.getDescription());
                                                    });
                                        }
                                ); break;
                        }
                    });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readCollection(dis,
                    () -> resume.addContact(Contacts.valueOf(dis.readUTF()),dis.readUTF(), dis.readUTF()));
            readCollection(dis,
                    () -> {
                        SectionType sectionType = SectionType.valueOf(dis.readUTF());
                        resume.addSection(sectionType, readSection(dis, sectionType));
                    });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENTS:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new OrganizationsSection(
                        readList(dis,
                                () -> new Organization(
                                    new Link(dis.readUTF(), dis.readUTF()),
                                        readList(dis,
                                        () -> new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF())
                                        )
                                    )
                                )
                        );
             default: return null;
        }
    }

    private <T> List<T> readList(DataInputStream dis, CollectionReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i ++){
            list.add(reader.read());
        }

        return list;
    }

    private <T> void writeCollection (DataOutputStream dos, Collection<T> col, CollectionWriter<T> writer) throws IOException {
        dos.writeInt(col.size());
        for (T item : col) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dis, Action action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.run();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonthValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt() , 1);
    }

    private interface Action {
        void run() throws IOException;
    }

    private interface CollectionWriter<T> {
        void write(T item) throws IOException;
    }

    private interface CollectionReader<T> {
        T read() throws IOException;
    }
}