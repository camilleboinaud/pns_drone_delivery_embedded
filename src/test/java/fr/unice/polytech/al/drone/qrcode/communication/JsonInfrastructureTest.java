package fr.unice.polytech.al.drone.qrcode.communication;

import fr.unice.polytech.al.drone.qrcode.communication.mapping.DataRequestMapperAPI;
import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import fr.unice.polytech.al.drone.qrcode.utils.StorageTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by camille on 06/11/15.
 */
public class JsonInfrastructureTest {

    private static final Logger logger = LogManager.getLogger(JsonInfrastructureTest.class.getName());
    private DataRequestMapperAPI storageManager;

    @BeforeClass
    public static void setUpClass(){
        logger.entry();
        StorageManagerFactory.reset();
        logger.info("Set up class method completed");
    }

    @AfterClass
    public static void tearDownClass(){
        logger.exit();
        logger.info("Tear down class method completed");
    }

    @Before
    public void setUp(){
        try {
            storageManager = StorageManagerFactory.getStorageManager(StorageTypeEnum.JSON);
        } catch (StorageManagerException e){
            e.printStackTrace();
            logger.error("Set up test method catched a StorageManagerException exception");
            fail("Set up test method should not throw this exception");
        }
        logger.info("Set up test method completed");
    }

    @After
    public void tearDown(){
        StorageManagerFactory.reset();
        logger.info("Tear down test method completed");
    }

    @Test
    public void readWriteFlightPlanTest() {

        logger.info("Entering readWriteFlightPlanTest");

        FlightPlan flightPlan = new FlightPlan();

        logger.info("Writing flight plan into JSON file");
        storageManager.writeFlightPlan(flightPlan);

        logger.info("Check if JSON file exists");
        assertTrue((new File(storageManager.FILE_PATH)).exists());
        logger.info("Check if file output match to flight plane object written");
        assertEquals(flightPlan, storageManager.readFlightPlan());

        logger.info("Execution of readWriteFlightPlanTest completed");

    }
}
