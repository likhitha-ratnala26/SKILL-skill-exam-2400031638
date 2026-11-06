package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import com.klef.soa.entity.Hostel;
import com.klef.soa.repository.HostelRepository;

@DataJpaTest
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:5432/skill_exam_2400031638",
    "spring.datasource.username=postgres",
    "spring.datasource.password=Likhitha@26",
    "spring.datasource.driver-class-name=org.postgresql.Driver",
    "spring.jpa.hibernate.ddl-auto=update"
})
public class HostelRepositoryTest {

    @Autowired
    private HostelRepository repository;

    private Hostel hostel1;
    private Hostel hostel2;
    private long initialCount;

    @BeforeEach
    void setup() {

        initialCount = repository.count();

        hostel1 = new Hostel();
        hostel1.setName("KL Boys Hostel");
        hostel1.setType("Boys");
        hostel1.setStatus(true);
        hostel1.setLocation("Vijayawada");

        hostel2 = new Hostel();
        hostel2.setName("KL Girls Hostel");
        hostel2.setType("Girls");
        hostel2.setStatus(true);
        hostel2.setLocation("Guntur");

        hostel1 = repository.saveAndFlush(hostel1);
        hostel2 = repository.saveAndFlush(hostel2);
    }

    // Test Case 1 - Find All
    @Test
    void testFindAllHostels() {

        List<Hostel> hostels = repository.findAll();

        assertNotNull(hostels);
        assertFalse(hostels.isEmpty());

        assertTrue(hostels.stream()
                .anyMatch(h -> h.getId().equals(hostel1.getId())));

        System.out.println("Total Hostels: " + hostels.size());
    }

    // Test Case 2 - Find By ID
    @Test
    void testFindHostelById() {

        Optional<Hostel> hostel =
                repository.findById(hostel1.getId());

        assertTrue(hostel.isPresent());

        assertEquals(hostel1.getId(), hostel.get().getId());

        Optional<Hostel> invalidHostel =
                repository.findById(-1L);

        assertFalse(invalidHostel.isPresent());

        System.out.println("Hostel ID: " + hostel.get().getId());
        System.out.println("Hostel Name: " + hostel.get().getName());
    }

    // Test Case 3 - Derived Query
    @Test
    void testFindHostelByNameAndType() {

        List<Hostel> byName =
                repository.findByName("KL Boys Hostel");

        assertNotNull(byName);
        assertFalse(byName.isEmpty());

        assertTrue(byName.stream()
                .anyMatch(h -> h.getId().equals(hostel1.getId())));

        assertEquals("KL Boys Hostel", hostel1.getName());

        List<Hostel> byType =
                repository.findByType("Girls");

        assertNotNull(byType);
        assertFalse(byType.isEmpty());

        assertTrue(byType.stream()
                .anyMatch(h -> h.getId().equals(hostel2.getId())));

        assertEquals("Girls", hostel2.getType());

        System.out.println("Hostel Name: " + hostel1.getName());
        System.out.println("Hostel Type: " + hostel2.getType());
    }

    // Test Case 4 - Count
    @Test
    void testTotalHostelCount() {

        long count = repository.count();

        assertEquals(initialCount + 2, count);

        System.out.println("Total Hostels: " + count);
    }

    // Test Case 5 - Entity Details
    @Test
    void testHostelDetails() {

        Optional<Hostel> hostel =
                repository.findById(hostel1.getId());

        assertTrue(hostel.isPresent());

        Hostel foundHostel = hostel.get();

        assertNotNull(foundHostel.getId());
        assertNotNull(foundHostel.getName());
        assertNotNull(foundHostel.getType());
        assertNotNull(foundHostel.getLocation());
        assertNotNull(foundHostel.getCreatedAt());
        assertNotNull(foundHostel.getUpdatedAt());

        assertEquals("KL Boys Hostel", foundHostel.getName());
        assertEquals("Boys", foundHostel.getType());
        assertEquals("Vijayawada", foundHostel.getLocation());
        assertTrue(foundHostel.isStatus());

        System.out.println("Name: " + foundHostel.getName());
        System.out.println("Type: " + foundHostel.getType());
        System.out.println("Status: " + foundHostel.isStatus());
        System.out.println("Location: " + foundHostel.getLocation());
    }
}