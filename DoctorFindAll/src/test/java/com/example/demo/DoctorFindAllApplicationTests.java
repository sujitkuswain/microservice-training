package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.pojo.Doctor;
import com.example.demo.repository.DoctorRepository;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorFindAllApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorRepository repo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAllDoctorsByHospitalId() throws Exception {

        Doctor d1 = new Doctor(1, "Dname1", "Special1", 101);
        Doctor d2 = new Doctor(2, "Dname2", "Special2", 101);


        List<Doctor> doctors = List.of(d1, d2);
        when(repo.findByHospitalId(101)).thenReturn(doctors);


        mockMvc.perform(get("/doctor/doctors/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$[0].doctorName", is("Dname1")));  
    }
}
