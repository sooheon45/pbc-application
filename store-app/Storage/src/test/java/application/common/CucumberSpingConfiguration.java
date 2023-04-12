package application.common;

import application.StorageApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { StorageApplication.class })
public class CucumberSpingConfiguration {}
